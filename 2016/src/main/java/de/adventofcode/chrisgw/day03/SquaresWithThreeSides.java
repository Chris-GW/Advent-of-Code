package de.adventofcode.chrisgw.day03;

import java.util.ArrayList;
import java.util.List;


public class SquaresWithThreeSides {


    public static List<Triangle> parseTriangleDocumentHorizontal(String triangleDocument) {
        String[] triangleRows = triangleDocument.split("\n");
        List<Triangle> triangles = new ArrayList<>(triangleRows.length);

        for (String triangleRow : triangleRows) {
            String[] triangleSides = triangleRow.trim().split("\\s+");
            int a = Integer.parseInt(triangleSides[0]);
            int b = Integer.parseInt(triangleSides[1]);
            int c = Integer.parseInt(triangleSides[2]);

            Triangle triangle = new Triangle(a, b, c);
            triangles.add(triangle);
        }
        return triangles;
    }

    public static List<Triangle> parseTriangleDocumentVertical(String triangleDocument) {
        String[] triangleRows = triangleDocument.split("\n");
        List<Triangle> triangles = new ArrayList<>(triangleRows.length);

        for (int rowIndex = 0; rowIndex < triangleRows.length; rowIndex += 3) {
            String[] triangleSidesA = triangleRows[rowIndex].trim().split("\\s+");
            String[] triangleSidesB = triangleRows[rowIndex + 1].trim().split("\\s+");
            String[] triangleSidesC = triangleRows[rowIndex + 2].trim().split("\\s+");

            for (int columnIndex = 0; columnIndex < triangleSidesA.length; columnIndex++) {
                int a = Integer.parseInt(triangleSidesA[columnIndex]);
                int b = Integer.parseInt(triangleSidesB[columnIndex]);
                int c = Integer.parseInt(triangleSidesC[columnIndex]);

                Triangle triangle = new Triangle(a, b, c);
                triangles.add(triangle);
            }
        }
        return triangles;
    }


    public static class Triangle {

        public int a = 0;
        public int b = 0;
        public int c = 0;


        public Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }


        public boolean isValidTriangle() {
            return a + b > c && a + c > b && b + c > a;
        }

    }


}
