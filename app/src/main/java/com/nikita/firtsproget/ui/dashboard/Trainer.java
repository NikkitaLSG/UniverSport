package com.nikita.firtsproget.ui.dashboard;


    public class Trainer {
        private String name;
        private String position;
        private String experience;
        private float rating;
        private int photoId;

        public Trainer(String name, String position, String experience, float rating, int photoId) {
            this.name = name;
            this.position = position;
            this.experience = experience;
            this.rating = rating;
            this.photoId = photoId;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public String getExperience() {
            return experience;
        }

        public float getRating() {
            return rating;
        }

        public int getPhotoId() {
            return photoId;
        }
    }

