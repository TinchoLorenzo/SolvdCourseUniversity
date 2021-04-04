package com.solvd.UniversityMvn.courses;

import java.util.Arrays;

public enum GPA {
	
	E(0.0, 0.5999999),
	D(0.6, 0.699999),
	C(0.7, 0.799999),
	B(0.8, 0.899999),
	A(0.9, 1.0);
	
	private double min;
	private double max;
	
	private GPA(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public String getRange() {
        return String.format("%d,%d", min, max);
    }

    public static GPA get(double val) {
        return Arrays.stream(values())
                     .filter(r -> val >= r.min && val <= r.max)
                     .findFirst()
                     .orElse(null);
    }

    public double min() {
    	return min;
    }
    
    public double max() {
    	return max;
    }
}
