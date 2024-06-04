package com.example.provaoficial2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateCapitalMap {
    private static final Map<String, String> stateCapitalMap = new HashMap<>();

    static {
        stateCapitalMap.put("Acre", "Rio Branco");
        stateCapitalMap.put("Alagoas", "Maceió");
        stateCapitalMap.put("Amapá", "Macapá");
        stateCapitalMap.put("Amazonas", "Manaus");
        stateCapitalMap.put("Bahia", "Salvador");
        stateCapitalMap.put("Ceará", "Fortaleza");
        stateCapitalMap.put("Distrito Federal", "Brasília");
        stateCapitalMap.put("Espírito Santo", "Vitória");
        stateCapitalMap.put("Goiás", "Goiânia");
        stateCapitalMap.put("Maranhão", "São Luís");
        stateCapitalMap.put("Mato Grosso", "Cuiabá");
        stateCapitalMap.put("Mato Grosso do Sul", "Campo Grande");
        stateCapitalMap.put("Minas Gerais", "Belo Horizonte");
        stateCapitalMap.put("Pará", "Belém");
        stateCapitalMap.put("Paraíba", "João Pessoa");
        stateCapitalMap.put("Paraná", "Curitiba");
        stateCapitalMap.put("Pernambuco", "Recife");
        stateCapitalMap.put("Piauí", "Teresina");
        stateCapitalMap.put("Rio de Janeiro", "Rio de Janeiro");
        stateCapitalMap.put("Rio Grande do Norte", "Natal");
        stateCapitalMap.put("Rio Grande do Sul", "Porto Alegre");
        stateCapitalMap.put("Rondônia", "Porto Velho");
        stateCapitalMap.put("Roraima", "Boa Vista");
        stateCapitalMap.put("Santa Catarina", "Florianópolis");
        stateCapitalMap.put("São Paulo", "São Paulo");
        stateCapitalMap.put("Sergipe", "Aracaju");
        stateCapitalMap.put("Tocantins", "Palmas");
    }

    public static String getCapitalByState(String state) {
        return stateCapitalMap.get(state);
    }

    public static List<String> getAllStates() {
        return new ArrayList<>(stateCapitalMap.keySet());
    }

    public static List<String> getCitiesInState(String selectedState) {
        // Verificar se o estado está no mapa
        if (stateCapitalMap.containsKey(selectedState)) {
            // Implemente a lógica para obter as cidades para o estado selecionado
            // Por enquanto, retornaremos uma lista vazia
            return new ArrayList<>();
        } else {
            return null; // Estado não encontrado no mapa
        }
    }
}
