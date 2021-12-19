package com.example.brief3_mutuellecentralisee.helpers;

import com.example.brief3_mutuellecentralisee.models.CountryCode;
import com.example.brief3_mutuellecentralisee.models.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class jsonHelper {

    public static List<CountryCode> GetPhoneCountryCodes() throws IOException, URISyntaxException {
        String jsonText=fileHelper.ReadAllText("codePays.json");
        Gson gson = new Gson();
        // retourn le type generic collection<CountryCode>
        Type datasetListType = new TypeToken<Collection<CountryCode>>() {}.getType();
        //convertir le text json dans le fichier vers un objet de type List<CountryCode>
        List<CountryCode> phoneCodes = gson.fromJson(jsonText, datasetListType);
        return phoneCodes;
    }
}
