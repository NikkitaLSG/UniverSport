package com.nikita.firtsproget.ui.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dieta {
    private List<String> diets;
    private boolean[] excludedDiets;

    public Dieta() {
        diets = new ArrayList<>();
        initializeDiets();
        excludedDiets = new boolean[diets.size()]; // Инициализация массива
    }

    private void initializeDiets() {
        diets.add("Средиземноморская диета");
        diets.add("Кето-диета");
        diets.add("Палео-диета");
        diets.add("Веганская диета");
        diets.add("Вегетарианская диета");
        diets.add("Диета Дюкана");
        diets.add("Низкоуглеводная диета");
        diets.add("Диета Аткинса");
        diets.add("Суперфуд диета");
        diets.add("Сбалансированная диета");
        diets.add("Диета с высоким содержанием белка");
        diets.add("Диета по группе крови");
        diets.add("Соковая диета");
        diets.add("Диета с интервалами голодания");
        diets.add("Гречневая диета");
    }

    // Метод для исключения диет
    public void setExcludedDiets(boolean[] excludedDiets) {
        this.excludedDiets = excludedDiets;
    }

    public List<String> getDiets() {
        return diets;
    }

    public String getRandomDiet() {
        Random random = new Random();
        List<String> availableDiets = new ArrayList<>();

        // Соберите доступные диеты
        for (int i = 0; i < diets.size(); i++) {
            if (!excludedDiets[i]) {
                availableDiets.add(diets.get(i));
            }
        }
        // Если доступные диеты есть, верните случайную
        if (!availableDiets.isEmpty()) {
            return availableDiets.get(random.nextInt(availableDiets.size()));
        } else {
            return "Нет доступных диет"; // Если все диеты исключены
        }
    }
}
