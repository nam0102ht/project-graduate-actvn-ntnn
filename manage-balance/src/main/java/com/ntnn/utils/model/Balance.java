package com.ntnn.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.json.JsonObject;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Balance {
    public long id;
    public long userId;
    public String cardNumber;
    public double lastAmount;
    public double currentAmount;
    public String lastUpdate;

    public JsonObject toJson() {
        return new JsonObject()
                .put("id", id)
                .put("userId", userId)
                .put("cardNumber", cardNumber)
                .put("lastAmount", lastAmount)
                .put("currentAmount", currentAmount)
                .put("lastUpdate", lastUpdate);
    }
}
