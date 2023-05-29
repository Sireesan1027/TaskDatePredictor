package com.example.TaskDateCalculater.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class HeaderItem {
        private String property;
        private boolean display;
        private String type;
        private String name;

        public HeaderItem(String property, boolean display, String type, String name) {
            this.property = property;
            this.display = display;
            this.type = type;
            this.name = name;
        }

        // getters and setters

}
