package com.ntnn.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.json.JsonObject;
import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String username;
    private String cardNumber;
    private String password;
    private String pin; //need hashing by argon2
    private long balanceId;
    private String idNumber; // accountId, CMND
    private String phone;
    private String email;
    private boolean isAuthenticate;
    private String dateOfBirth;
    private String createDate;
    private String updateDate;

    public JsonObject toJson() {
        return new JsonObject()
                .put("id", id)
                .put("name", name)
                .put("cardNumber", cardNumber)
                .put("password", password)
                .put("pin", pin)
                .put("balanceId", balanceId)
                .put("idNumber", idNumber)
                .put("phone", phone)
                .put("email", email)
                .put("isAuthenticate", isAuthenticate)
                .put("dateOfBirth", dateOfBirth)
                .put("createDate", createDate)
                .put("updateDate", updateDate);
    }
}
