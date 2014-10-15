/**
 * 
 */
package org.jutility.math.geometry;

/*
 * #%L
 * jutility-math
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jutility.math.vectorAlgebra.IPoint2;
import org.jutility.math.vectorAlgebra.ITuple2;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
public class DelaunayTriangulation2 {


    /**
     * Calculates the Delaunay Triangulation of a set of {@link IPoint2 Points}.
     * 
     * @param points
     *            the points to triangulate.
     * @return the lines of the Delaunay Triangulation.
     */
    public <T extends Number> List<ILine2<T>> triangulate(
            List<IPoint2<T>> points) {

        Collections.sort(points, ITuple2.byX);

        return triangulate(points, 0, points.size());
    }


    private <T extends Number> List<ILine2<T>> triangulate(
            List<IPoint2<T>> points, int lowerBound, int upperBound) {

        int difference = upperBound - lowerBound;

        if (difference > 3) {

            int middle = lowerBound + difference / 2;

            List<ILine2<T>> leftTriangles = triangulate(points, lowerBound,
                    middle);
            List<ILine2<T>> rightTriangles = triangulate(points, middle + 1,
                    upperBound);

            return merge(leftTriangles, rightTriangles);
        }

        List<ILine2<T>> lines = new ArrayList<ILine2<T>>();

        lines.add(new Line2<T>(points.get(lowerBound), points
                .get(lowerBound + 1), points.get(lowerBound).getType()));

        if (difference == 3) {

            lines.add(new Line2<T>(points.get(lowerBound + 1), points
                    .get(lowerBound + 2), points.get(lowerBound).getType()));

            lines.add(new Line2<T>(points.get(lowerBound), points
                    .get(lowerBound + 2), points.get(lowerBound).getType()));
        }

        return lines;
    }


    private <T extends Number> List<ILine2<T>> merge(
            List<ILine2<T>> leftTriangles, List<ILine2<T>> rightTriangles) {

        
        // find base line
        
        // find left candidate point
        
        // find right candidate point
        
        // find centroid of left triangle
        
        // find centroid of right triangle
        
        // check whether
        
        return null;
    }

}
