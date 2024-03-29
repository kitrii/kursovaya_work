package client.api;

import client.models.Bond;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** Класс Api - связь с сервером по HTTP протоколу*/
/** Cвязь с сервером по HTTP*/
public class Api {
    private final String HOST = "http://localhost:8080";

    /** Метод добавления облигации */
    public Boolean addBond(String bondId, String bondName, String nominalCost,
                           String couponRate, String repaymentPeriod,
                           String couponFrequency, String yieldToMaturity) {
        Map<String, String> map = new HashMap<>();
        map.put("bondName", bondName);
        map.put("bondId", bondId);
        map.put("nominalCost", nominalCost);
        map.put("couponFrequency", couponFrequency);
        map.put("repaymentPeriod", repaymentPeriod);
        map.put("couponRate", couponRate);
        map.put("yieldToMaturity", yieldToMaturity);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        String URL = String.format("%s/bonds/add", HOST);
        String response = HttpRequest.sendPost(URL, json);
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response).getAsJsonObject();
            if (jsonResult.get("success").getAsBoolean()) {
                return true;
            }
        }
        return false;
    }
    public Boolean addBondInPortfolio(String bondId, String ownerId) {
        Map<String, String> map = new HashMap<>();
        map.put("bondId", bondId);
        map.put("ownerId", ownerId);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        String URL = String.format("%s/portfolio/add", HOST);
        String response = HttpRequest.sendPost(URL, json);
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response).getAsJsonObject();
            if (jsonResult.get("success").getAsBoolean()) {
                return true;
            }
        }
        return false;
    }

    /** Получаем всю информацию об облигациях */
    public List<Bond> getAllBonds() {
        String URL = HOST + "/bonds/all";
        List<Bond> result = new ArrayList<>();
        String response = HttpRequest.sendGet(URL);

        if (response != null) {
            JsonArray jsonBondArray = JsonParser.parseString(response).getAsJsonArray();
            for (int i = 0; i < jsonBondArray.size(); i++) {
                JsonObject bondJson = jsonBondArray.get(i).getAsJsonObject();
                Bond bond = new Bond(
                        bondJson.get("bondName").getAsString(),
                        bondJson.get("bondId").getAsInt(),
                        bondJson.get("nominalCost").getAsInt(),
                        bondJson.get("couponFrequency").getAsInt(),
                        bondJson.get("repaymentPeriod").getAsInt(),
                        bondJson.get("couponRate").getAsInt(),
                        bondJson.get("yieldToMaturity").getAsInt()
                );
                result.add(bond);
            }
        }

        return result;
    }


    /** Получаем всю информацию об облигациях для определенного владельца*/
    public List<Bond> getBondsByOwnerId(String ownerId) {
        String URL = String.format("%s/portfolio/owner?ownerId=%s", HOST, ownerId);
        List<Bond> result = new ArrayList<>();
        String response = HttpRequest.sendGet(URL);

        if (response != null) {
            JsonArray jsonBondArray = JsonParser.parseString(response).getAsJsonArray();
            for (int i = 0; i < jsonBondArray.size(); i++) {
                JsonObject bondJson = jsonBondArray.get(i).getAsJsonObject();
                Bond bond = new Bond(
                        bondJson.get("bondName").getAsString(),
                        bondJson.get("bondId").getAsInt(),
                        bondJson.get("nominalCost").getAsInt(),
                        bondJson.get("couponFrequency").getAsInt(),
                        bondJson.get("repaymentPeriod").getAsInt(),
                        bondJson.get("couponRate").getAsInt(),
                        bondJson.get("yieldToMaturity").getAsInt()
                );
                result.add(bond);
            }
        }

        return result;
    }

    /** Удаляем облигацию у определенного владельца */
    public boolean deleteBond(String bondId) {
        String URL = String.format("%s/bonds/delete?bondId=%s", HOST, bondId);
        String response = HttpRequest.sendDelete(URL);
        if (response != null) {
            return true;
        }
        return false;
    }

    public boolean editBond(String bondId, String bondName, String nominalCost,
                            String couponRate, String repaymentPeriod,
                            String couponFrequency, String yieldToMaturity
                            ) {
        String URL = String.format("%s/bonds/edit", HOST);
        Map<String, String> map = new HashMap<>();
        map.put("bondName", bondName);
        map.put("bondId", bondId);
        map.put("nominalCost", nominalCost);
        map.put("couponFrequency", couponFrequency);
        map.put("repaymentPeriod", repaymentPeriod);
        map.put("couponRate", couponRate);
        map.put("yieldToMaturity", yieldToMaturity);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        String response = HttpRequest.sendPut(URL, json);
        if (response != null) {
            return true;
        }
        return false;
    }

    public String calculatePortfolio(String ownerId) {
        String URL = String.format("%s/portfolio/calculateDuration?ownerId=%s", HOST, ownerId);
        String response = HttpRequest.sendGet(URL);

        return response;
    }}