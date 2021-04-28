package com.ntnn.utils.utils;

import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j2;
import org.jpos.iso.*;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO93APackager;
import org.jpos.iso.packager.NativePackager;

import javax.rmi.CORBA.Util;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Log4j2
public class ISOMsgUtils {

    static final String PACKED = "3031303045323030303030303032303030303030303030303030303030303032303030303130303933393338323735383131313131313030323530343230323132303030313965793131333534353435416273676335733636";

    /*
    * given obj
    * return hex
    * */
    public static String MsgToString(JsonObject obj) {
        ISOMsg isoMsg = new ISOMsg();
        String result = "";
        try {
            ISOPackager packager = new ISO93APackager();
            obj.iterator().forEachRemaining(v -> {
                isoMsg.set(Integer.parseInt(v.getKey()), (String) v.getValue());
            });
            isoMsg.setPackager(packager);
            result = ISOUtil.byte2hex(isoMsg.pack());
        } catch (ISOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /*
    * given(packed)
    * return isoMsg
    * */
    public static ISOMsg unpack(String packedHex) {
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setPackager(new ISO93APackager());
            isoMsg.unpack(ISOUtil.hex2byte(packedHex));
        } catch (ISOException ex) {
            log.info(ex.getMessage());
        }
        return isoMsg;
    }

    public static JsonObject parseToJson(ISOMsg isoMsg) {
        Map<Integer, Object> mapValues = isoMsg.getChildren();
        JsonObject obj = new JsonObject();
        mapValues.forEach((k, v) -> {
            obj.put(String.valueOf(k), isoMsg.getValue(k).toString());
        });
        return obj;
    }

    public static void main(String[] args) {
        System.out.println(MsgToString(new JsonObject()
                .put("0", "0100")
                .put("2", "970412066262718")
                .put("3", "111111")
                .put("7", ISODate.formatDate(new Date(), "ddMMyyyy"))
                .put("39", "200")
                .put("111", "ey11354545Absgc5s66")
        ));
        log.info(parseToJson(unpack(PACKED)));
    }
}
