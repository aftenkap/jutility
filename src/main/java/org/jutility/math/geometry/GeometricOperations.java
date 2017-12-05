package org.jutility.math.geometry;


//@formatter:off
/*
* #%L
 * * jutility-math
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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
//@formatter:on



import java.util.ArrayList;
import java.util.List;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.arithmetics.ArithmeticOperations;
import org.jutility.math.vectoralgebra.IMatrix4;
import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.ITuple4;
import org.jutility.math.vectoralgebra.IVector4;
import org.jutility.math.vectoralgebra.Matrix4;
import org.jutility.math.vectoralgebra.Point4;
import org.jutility.math.vectoralgebra.Vector4;
import org.jutility.math.vectoralgebra.VectorAlgebraicOperations;



/**
 * The {@code GeometricOperations} class provides a convenience wrapper around
 * the {@link VectorAlgebraicOperations} class.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class GeometricOperations {


    /**
     * Scales the {@link IVector4 Vector} by the provided {@link Number Scalar}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector} to
     *            scale.
     * 
     * @param vectorToScale
     *            The {@link IVector4 Vector} to scale.
     * @param scalar
     *            The {@link Number Scalar}.
     * @return The scaled {@link IVector4 Vector}.
     */
    public static final <T extends Number> IVector4<T> scale(
            final IVector4<T> vectorToScale, final Number scalar) {

        return VectorAlgebraicOperations.multiply(vectorToScale, scalar,
                vectorToScale.getType());
    }

    /**
     * Scales the {@link IVector4 Vector} by the provided {@link Number Scalar}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link IVector4 Vector}.
     * 
     * @param vectorToScale
     *            The {@link IVector4 Vector} to scale.
     * @param scalar
     *            The {@link Number Scalar}.
     * @param returnType
     *            the desired return type.
     * @return The scaled {@link IVector4 Vector}.
     */
    public static final <R extends Number> IVector4<R> scale(
            final IVector4<?> vectorToScale, final Number scalar,
            Class<? extends R> returnType) {

        return VectorAlgebraicOperations.multiply(vectorToScale, scalar,
                returnType);
    }


    /**
     * Scales the {@link IVector4 Vector} by the provided {@link Number Scalar}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector} to
     *            scale.
     * 
     * @param scalar
     *            The {@link Number Scalar}.
     * @param vectorToScale
     *            The {@link IVector4 Vector} to scale.
     * @return The scaled {@link IVector4 Vector}.
     */
    public static final <T extends Number> IVector4<T> scale(
            final Number scalar, final IVector4<T> vectorToScale) {

        return VectorAlgebraicOperations.multiply(scalar, vectorToScale,
                vectorToScale.getType());
    }


    /**
     * Scales the {@link IVector4 Vector} by the provided {@link Number Scalar}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link IVector4 Vector}.
     * 
     * @param scalar
     *            The {@link Number Scalar}.
     * @param vectorToScale
     *            The {@link IVector4 Vector} to scale.
     * @param returnType
     *            the desired return type.
     * @return The scaled {@link IVector4 Vector}.
     */
    public static final <R extends Number> IVector4<R> scale(
            final Number scalar, final IVector4<?> vectorToScale,
            Class<? extends R> returnType) {

        return VectorAlgebraicOperations.multiply(scalar, vectorToScale,
                returnType);
    }


    /**
     * Reverts the scaling of the {@link IVector4 Vector} by the provided
     * {@link Number Scalar}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector} to
     *            scale.
     * 
     * @param vectorToScale
     *            the {@link IVector4 Vector} to scale.
     * @param scalar
     *            the {@link Number Scalar}.
     * @return the scaled {@link IVector4 Vector}.
     */
    public static final <T extends Number> IVector4<T> revertScale(
            final IVector4<T> vectorToScale, final Number scalar) {

        Number inverseScalar = ArithmeticOperations.divide(1.0, scalar,
                scalar.getClass());

        return VectorAlgebraicOperations.multiply(inverseScalar, vectorToScale,
                vectorToScale.getType());
    }

    /**
     * Reverts the scaling of the {@link IVector4 Vector} by the provided
     * {@link Number Scalar}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link IVector4 Vector}.
     * 
     * @param vectorToScale
     *            the {@link IVector4 Vector} to scale.
     * @param scalar
     *            the {@link Number Scalar}.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link IVector4 Vector}.
     */
    public static final <R extends Number> IVector4<R> revertScale(
            final IVector4<?> vectorToScale, final Number scalar,
            final Class<? extends R> returnType) {

        Number inverseScalar = ArithmeticOperations.divide(1.0, scalar,
                returnType);

        return VectorAlgebraicOperations.multiply(inverseScalar, vectorToScale,
                returnType);
    }



    /**
     * Translates the {@link IPoint4 Point} by the provided {@link ITranslation
     * Translation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            translate.
     * 
     * @param pointToTranslate
     *            the {@link IPoint4 Point} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @return the translated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> translate(
            final IPoint4<T> pointToTranslate, final ITranslation<?> translation) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .translationMatrix(translation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate, pointToTranslate.getType());
    }

    /**
     * Translates the {@link IPoint4 Point} by the provided {@link ITranslation
     * Translation}.
     * 
     * @param <R>
     *            the {@link Number} type of the translated {@link IPoint4
     *            Point}.
     * 
     * @param pointToTranslate
     *            the {@link IPoint4 Point} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @param returnType
     *            the desired return type.
     * @return the translated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> translate(
            final IPoint4<?> pointToTranslate,
            final ITranslation<?> translation, Class<? extends R> returnType) {

        IMatrix4<R> translationMatrix = GeometricOperations.translationMatrix(
                translation, returnType);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate, returnType);
    }


    /**
     * Reverts the translation of a {@link IPoint4 Point} by a given
     * {@link ITranslation Translation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            translate.
     * 
     * @param pointToTranslate
     *            the {@link IPoint4 Point} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @return the translated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> revertTranslate(
            final IPoint4<T> pointToTranslate, final ITranslation<?> translation) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(translation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate, pointToTranslate.getType());
    }

    /**
     * Reverts the translation of a {@link IPoint4 Point} by a given
     * {@link ITranslation Translation}.
     * 
     * @param <R>
     *            the {@link Number} type of the translated {@link IPoint4
     *            Point}.
     * 
     * @param pointToTranslate
     *            the {@link IPoint4 Point} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @param returnType
     *            the desired return type.
     * @return the translated point.
     */
    public static final <R extends Number> IPoint4<R> revertTranslate(
            final IPoint4<?> pointToTranslate,
            final ITranslation<?> translation, Class<? extends R> returnType) {

        IMatrix4<R> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(translation, returnType);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate, returnType);
    }



    /**
     * Shears the {@link IPoint4 Point} by the provided {@link IShearFactor
     * ShearFactor Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to shear.
     * 
     * @param pointToShear
     *            the {@link IPoint4 Point} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @return the sheared {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> shear(
            final IPoint4<T> pointToShear, final IShearFactor<?> shearFactor) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .shearMatrix(shearFactor);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear,
                pointToShear.getType());
    }

    /**
     * Shears the {@link IPoint4 Point} by the provided {@link IShearFactor
     * ShearFactor Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the sheared {@link IPoint4 Point}.
     * 
     * @param pointToShear
     *            the {@link IPoint4 Point} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @param returnType
     *            the desired return type.
     * @return the sheared {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> shear(
            final IPoint4<?> pointToShear, final IShearFactor<?> shearFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> shearMatrix = GeometricOperations.shearMatrix(shearFactor,
                returnType);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear,
                returnType);
    }


    /**
     * Reverts the shear of the {@link IPoint4 Point} by the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to shear.
     * 
     * @param pointToShear
     *            the {@link IPoint4 Point} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @return the sheared {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> revertShear(
            final IPoint4<T> pointToShear, final IShearFactor<?> shearFactor) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .inverseShearMatrix(shearFactor);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear,
                pointToShear.getType());
    }

    /**
     * Reverts the shear of the {@link IPoint4 Point} by the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the sheared {@link IPoint4 Point}.
     * 
     * @param pointToShear
     *            the {@link IPoint4 Point} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @param returnType
     *            the desired return type.
     * @return the sheared {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> revertShear(
            final IPoint4<?> pointToShear, final IShearFactor<?> shearFactor,
            Class<? extends R> returnType) {

        IMatrix4<R> shearMatrix = GeometricOperations.inverseShearMatrix(
                shearFactor, returnType);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear,
                returnType);
    }


    /**
     * Rotates the {@link IPoint4 Point} by the provided {@link IRotation
     * Angle-Axis Rotation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> rotate(
            final IPoint4<T> pointToRotate, final IRotation<?> rotation) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .rotationMatrix(rotation);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }

    /**
     * Rotates the {@link IPoint4 Point} by the provided {@link IRotation
     * Angle-Axis Rotation}.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> rotate(
            final IPoint4<?> pointToRotate, final IRotation<?> rotation,
            Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.rotationMatrix(
                rotation, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the {@link IPoint4 Point} by the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @return the rotated {@link IPoint4 Point}.
     * 
     */
    public static final <T extends Number> IPoint4<T> revertRotate(
            final IPoint4<T> pointToRotate, final IRotation<?> rotation) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseRotationMatrix(rotation);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }

    /**
     * Reverts the rotation of the {@link IPoint4 Point} by the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> revertRotate(
            final IPoint4<?> pointToRotate, final IRotation<?> rotation,
            final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.inverseRotationMatrix(
                rotation, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }

    /**
     * Rotates the {@link IPoint4 Point} around the x-axis by the provided
     * rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> rotateX(
            final IPoint4<T> pointToRotate, final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations.xRotationMatrix(
                rotationAngle, pointToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }

    /**
     * Rotates the {@link IPoint4 Point} around the x-axis by the provided
     * rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> rotateX(
            final IPoint4<?> pointToRotate, final Number rotationAngle,
            Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.xRotationMatrix(
                rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the {@link IPoint4 Point} around the x-axis by
     * the provided rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> revertRotateX(
            final IPoint4<T> pointToRotate, final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations
                .inverseXRotationMatrix(rotationAngle, pointToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }

    /**
     * Reverts the rotation of the {@link IPoint4 Point} around the x-axis by
     * the provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> revertRotateX(
            final IPoint4<?> pointToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations
                .inverseXRotationMatrix(rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }



    /**
     * Rotates the {@link IPoint4 Point} around the y-axis by the provided
     * rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> rotateY(
            final IPoint4<T> pointToRotate, final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations.yRotationMatrix(
                rotationAngle, pointToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }


    /**
     * Rotates the {@link IPoint4 Point} around the y-axis by the provided
     * rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> rotateY(
            final IPoint4<?> pointToRotate, final Number rotationAngle,
            Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.yRotationMatrix(
                rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the {@link IPoint4 Point} around the y-axis by
     * the provided rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> revertRotateY(
            final IPoint4<T> pointToRotate, final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations
                .inverseYRotationMatrix(rotationAngle, pointToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }


    /**
     * Reverts the rotation of the {@link IPoint4 Point} around the y-axis by
     * the provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> revertRotateY(
            final IPoint4<?> pointToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations
                .inverseYRotationMatrix(rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }



    /**
     * Rotates the {@link IPoint4 Point} around the z-axis by the provided
     * rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> rotateZ(
            final IPoint4<T> pointToRotate, final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations.zRotationMatrix(
                rotationAngle, pointToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }


    /**
     * Rotates the {@link IPoint4 Point} around the z-axis by the provided
     * rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> rotateZ(
            final IPoint4<?> pointToRotate, final Number rotationAngle,
            Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.zRotationMatrix(
                rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the {@link IPoint4 Point} around the z-axis by
     * the provided rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IPoint4 Point} to
     *            rotate.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <T extends Number> IPoint4<T> revertRotateZ(
            final IPoint4<T> pointToRotate, final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations
                .inverseZRotationMatrix(rotationAngle, pointToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, pointToRotate.getType());
    }


    /**
     * Reverts the rotation of the {@link IPoint4 Point} around the z-axis by
     * the provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link IPoint4 Point}.
     * 
     * @param pointToRotate
     *            the {@link IPoint4 Point} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link IPoint4 Point}.
     */
    public static final <R extends Number> IPoint4<R> revertRotateZ(
            final IPoint4<?> pointToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations
                .inverseZRotationMatrix(rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }



    /**
     * Translates the {@link ILine4 Line} by the provided {@link ITranslation
     * Translation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to
     *            translate.
     * 
     * @param lineToTranslate
     *            the {@link ILine4 Line} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @return the translated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> translate(
            final ILine4<T> lineToTranslate, final Translation<?> translation) {


        IPoint4<T> translatedSource = GeometricOperations.translate(
                lineToTranslate.getSource(), translation);

        IPoint4<T> translatedSink = GeometricOperations.translate(
                lineToTranslate.getSink(), translation);

        return new Line4<>(translatedSource, translatedSink,
                lineToTranslate.getType());
    }


    /**
     * Translates the {@link ILine4 Line} by the provided {@link ITranslation
     * Translation}.
     * 
     * @param <R>
     *            the {@link Number} type of the translated {@link ILine4 Line}.
     * 
     * @param lineToTranslate
     *            the {@link ILine4 Line} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @param returnType
     *            the desired return type.
     * @return the translated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> translate(
            final ILine4<?> lineToTranslate, final Translation<?> translation,
            final Class<? extends R> returnType) {


        IPoint4<R> translatedSource = GeometricOperations.translate(
                lineToTranslate.getSource(), translation, returnType);

        IPoint4<R> translatedSink = GeometricOperations.translate(
                lineToTranslate.getSink(), translation, returnType);


        return new Line4<>(translatedSource, translatedSink, returnType);
    }


    /**
     * Reverts the translation of the {@link ILine4 Line} by the provided
     * {@link ITranslation Translation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to
     *            translate.
     * 
     * @param lineToTranslate
     *            the {@link ILine4 Line} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @return the translated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertTranslate(
            final ILine4<T> lineToTranslate, final ITranslation<?> translation) {

        IPoint4<T> translatedSource = GeometricOperations.revertTranslate(
                lineToTranslate.getSource(), translation);

        IPoint4<T> translatedSink = GeometricOperations.revertTranslate(
                lineToTranslate.getSink(), translation);


        return new Line4<>(translatedSource, translatedSink,
                lineToTranslate.getType());
    }


    /**
     * Reverts the translation of the {@link ILine4 Line} by the provided
     * {@link ITranslation Translation}.
     * 
     * @param <R>
     *            the {@link Number} type of the translated {@link ILine4 Line}.
     * 
     * @param lineToTranslate
     *            the {@link ILine4 Line} to translate.
     * @param translation
     *            the {@link ITranslation Translation}.
     * @param returnType
     *            the desired return type.
     * @return the translated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertTranslate(
            final ILine4<?> lineToTranslate, final ITranslation<?> translation,
            Class<? extends R> returnType) {

        IPoint4<R> translatedSource = GeometricOperations.revertTranslate(
                lineToTranslate.getSource(), translation, returnType);

        IPoint4<R> translatedSink = GeometricOperations.revertTranslate(
                lineToTranslate.getSink(), translation, returnType);


        return new Line4<>(translatedSource, translatedSink, returnType);
    }


    /**
     * Scales the {@link ILine4 Line} by the provided {@link IScaleFactor
     * Scaling Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to scale.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link IScaleFactor Scaling Factor}.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> scaleFactor(
            final ILine4<T> lineToScale, final IScaleFactor<?> scalingFactor) {

        IMatrix4<?> scalingMatrix = GeometricOperations
                .scalingMatrix(scalingFactor);

        IPoint4<T> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), lineToScale.getType());

        IPoint4<T> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), lineToScale.getType());


        return new Line4<>(scaledSource, scaledSink, lineToScale.getType());
    }


    /**
     * Scales the {@link ILine4 Line} by the provided {@link IScaleFactor
     * Scaling Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ILine4 Line}.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link IScaleFactor Scaling Factor}.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> scaleFactor(
            final ILine4<?> lineToScale, final IScaleFactor<?> scalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scalingMatrix = GeometricOperations.scalingMatrix(
                scalingFactor, returnType);

        IPoint4<R> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), returnType);

        IPoint4<R> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), returnType);


        return new Line4<>(scaledSource, scaledSink, returnType);
    }

    /**
     * Reverts the scaling of the {@link ILine4 Line} by the provided
     * {@link IScaleFactor Scaling Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to scale.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link IScaleFactor Scaling Factor}.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertScale(
            final ILine4<T> lineToScale, final IScaleFactor<?> scalingFactor) {

        IMatrix4<?> scalingMatrix = GeometricOperations
                .inverseScalingMatrix(scalingFactor);

        IPoint4<T> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), lineToScale.getType());

        IPoint4<T> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), lineToScale.getType());


        return new Line4<>(scaledSource, scaledSink, lineToScale.getType());
    }

    /**
     * Reverts the scaling of the {@link ILine4 Line} by the provided
     * {@link IScaleFactor Scaling Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ILine4 Line}.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link IScaleFactor Scaling Factor}.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertScale(
            final ILine4<?> lineToScale, final IScaleFactor<?> scalingFactor,
            final Class<? extends R> returnType) {


        IMatrix4<R> scalingMatrix = GeometricOperations.inverseScalingMatrix(
                scalingFactor, returnType);

        IPoint4<R> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), returnType);

        IPoint4<R> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), returnType);


        return new Line4<>(scaledSource, scaledSink, returnType);
    }

    /**
     * Uniformly scales the {@link ILine4 Line} by the provided {@link Number
     * Scaling Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to scale.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link Number Scaling Factor}.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> linearScale(
            final ILine4<T> lineToScale, final Number scalingFactor) {

        IMatrix4<T> scalingMatrix = GeometricOperations.linearScalingMatrix(
                scalingFactor, lineToScale.getType());

        IPoint4<T> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), lineToScale.getType());

        IPoint4<T> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), lineToScale.getType());


        return new Line4<>(scaledSource, scaledSink, lineToScale.getType());
    }

    /**
     * Uniformly scales the {@link ILine4 Line} by the provided {@link Number
     * Scaling Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ILine4 Line}.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link Number Scaling Factor}.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> linearScale(
            final ILine4<?> lineToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scalingMatrix = GeometricOperations.linearScalingMatrix(
                scalingFactor, returnType);

        IPoint4<R> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), returnType);

        IPoint4<R> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), returnType);


        return new Line4<>(scaledSource, scaledSink, returnType);
    }

    /**
     * Reverts the uniform scaling of the {@link ILine4 Line} by the provided
     * {@link Number Scaling Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to scale.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link Number Scaling Factor}.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<? extends Number> revertLinearScale(
            final ILine4<T> lineToScale, final Number scalingFactor) {

        IMatrix4<T> scalingMatrix = GeometricOperations
                .inverseLinearScalingMatrix(scalingFactor,
                        lineToScale.getType());

        IPoint4<T> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), lineToScale.getType());

        IPoint4<T> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), lineToScale.getType());


        return new Line4<T>(scaledSource, scaledSink, lineToScale.getType());
    }

    /**
     * Reverts the uniform scaling of the {@link ILine4 Line} by the provided
     * {@link Number Scaling Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ILine4 Line}.
     * 
     * @param lineToScale
     *            the {@link ILine4 Line} to scale.
     * @param scalingFactor
     *            the {@link Number Scaling Factor}.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertLinearScale(
            final ILine4<?> lineToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scalingMatrix = GeometricOperations
                .inverseLinearScalingMatrix(scalingFactor, returnType);

        IPoint4<R> scaledSource = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSource(), returnType);

        IPoint4<R> scaledSink = VectorAlgebraicOperations.multiply(
                scalingMatrix, lineToScale.getSink(), returnType);


        return new Line4<>(scaledSource, scaledSink, returnType);
    }

    /**
     * Shears the {@link ILine4 Line} by the provided {@link IShearFactor
     * ShearFactor Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to shear.
     * 
     * @param lineToShear
     *            the {@link ILine4 Line} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @return the sheared {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> shear(
            final ILine4<T> lineToShear, final IShearFactor<?> shearFactor) {

        IPoint4<T> shearedSource = GeometricOperations.shear(
                lineToShear.getSource(), shearFactor);

        IPoint4<T> shearedSink = GeometricOperations.shear(
                lineToShear.getSink(), shearFactor);


        return new Line4<>(shearedSource, shearedSink, lineToShear.getType());
    }

    /**
     * Shears the {@link ILine4 Line} by the provided {@link IShearFactor
     * ShearFactor Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the sheared {@link ILine4 Line}.
     * 
     * @param lineToShear
     *            the {@link ILine4 Line} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @param returnType
     *            the desired return type.
     * @return the sheared {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> shear(
            final ILine4<?> lineToShear, final IShearFactor<?> shearFactor,
            final Class<? extends R> returnType) {

        IPoint4<R> shearedSource = GeometricOperations.shear(
                lineToShear.getSource(), shearFactor, returnType);

        IPoint4<R> shearedSink = GeometricOperations.shear(
                lineToShear.getSink(), shearFactor, returnType);


        return new Line4<>(shearedSource, shearedSink, returnType);
    }

    /**
     * Reverts the shear of the {@link ILine4 Line} by the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to shear.
     * 
     * @param lineToShear
     *            the {@link ILine4 Line} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @return the sheared {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertShear(
            final ILine4<T> lineToShear, final IShearFactor<?> shearFactor) {

        IPoint4<T> shearedSource = GeometricOperations.revertShear(
                lineToShear.getSource(), shearFactor);

        IPoint4<T> shearedSink = GeometricOperations.revertShear(
                lineToShear.getSink(), shearFactor);


        return new Line4<>(shearedSource, shearedSink, lineToShear.getType());
    }

    /**
     * Reverts the shear of the {@link ILine4 Line} by the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the sheared {@link ILine4 Line}.
     * 
     * @param lineToShear
     *            the {@link ILine4 Line} to shear.
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @param returnType
     *            the desired return type.
     * @return the sheared {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertShear(
            final ILine4<?> lineToShear, final IShearFactor<?> shearFactor,
            final Class<? extends R> returnType) {

        IPoint4<R> shearedSource = GeometricOperations.revertShear(
                lineToShear.getSource(), shearFactor, returnType);

        IPoint4<R> shearedSink = GeometricOperations.revertShear(
                lineToShear.getSink(), shearFactor, returnType);

        return new Line4<>(shearedSource, shearedSink, returnType);
    }

    /**
     * Rotates the {@link ILine4 Line} by the provided {@link IRotation
     * Angle-Axis Rotation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> rotate(
            final ILine4<T> lineToRotate, final IRotation<?> rotation) {

        IPoint4<T> rotatedSource = GeometricOperations.rotate(
                lineToRotate.getSource(), rotation);

        IPoint4<T> rotatedSink = GeometricOperations.rotate(
                lineToRotate.getSink(), rotation);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Rotates the {@link ILine4 Line} by the provided {@link IRotation
     * Angle-Axis Rotation}.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> rotate(
            final ILine4<?> lineToRotate, final Rotation<?> rotation,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.rotate(
                lineToRotate.getSource(), rotation, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.rotate(
                lineToRotate.getSink(), rotation, returnType);


        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} by the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertRotate(
            final ILine4<T> lineToRotate, final IRotation<?> rotation) {

        IPoint4<T> rotatedSource = GeometricOperations.revertRotate(
                lineToRotate.getSource(), rotation);

        IPoint4<T> rotatedSink = GeometricOperations.revertRotate(
                lineToRotate.getSink(), rotation);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} by the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<? extends Number> revertRotate(
            final ILine4<?> lineToRotate, final IRotation<?> rotation,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.revertRotate(
                lineToRotate.getSource(), rotation, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.revertRotate(
                lineToRotate.getSink(), rotation, returnType);

        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Rotates the {@link ILine4 Line} around the x-axis by the provided
     * rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> rotateX(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<T> rotatedSource = GeometricOperations.rotateX(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<T> rotatedSink = GeometricOperations.rotateX(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Rotates the {@link ILine4 Line} around the x-axis by the provided
     * rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> rotateX(
            final ILine4<?> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.rotateX(
                lineToRotate.getSource(), rotationAngle, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.rotateX(
                lineToRotate.getSink(), rotationAngle, returnType);


        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} around the x-axis by the
     * provided rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertRotateX(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<T> rotatedSource = GeometricOperations.revertRotateX(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<T> rotatedSink = GeometricOperations.revertRotateX(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} around the x-axis by the
     * provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertRotateX(
            final ILine4<?> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.revertRotateX(
                lineToRotate.getSource(), rotationAngle, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.revertRotateX(
                lineToRotate.getSink(), rotationAngle, returnType);

        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Rotates the {@link ILine4 Line} around the y-axis by the provided
     * rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> rotateY(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<T> rotatedSource = GeometricOperations.rotateY(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<T> rotatedSink = GeometricOperations.rotateY(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Rotates the {@link ILine4 Line} around the y-axis by the provided
     * rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> rotateY(
            final ILine4<?> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.rotateY(
                lineToRotate.getSource(), rotationAngle, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.rotateY(
                lineToRotate.getSink(), rotationAngle, returnType);


        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} around the y-axis by the
     * provided rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertRotateY(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<T> rotatedSource = GeometricOperations.revertRotateY(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<T> rotatedSink = GeometricOperations.revertRotateY(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} around the y-axis by the
     * provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertRotateY(
            final ILine4<?> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.revertRotateY(
                lineToRotate.getSource(), rotationAngle, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.revertRotateY(
                lineToRotate.getSink(), rotationAngle, returnType);

        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Rotates the {@link ILine4 Line} around the z-axis by the provided
     * rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> rotateZ(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<T> rotatedSource = GeometricOperations.rotateZ(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<T> rotatedSink = GeometricOperations.rotateZ(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Rotates the {@link ILine4 Line} around the z-axis by the provided
     * rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> rotateZ(
            final ILine4<?> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.rotateZ(
                lineToRotate.getSource(), rotationAngle, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.rotateZ(
                lineToRotate.getSink(), rotationAngle, returnType);


        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} around the z-axis by the
     * provided rotation angle.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ILine4 Line} to rotate.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <T extends Number> ILine4<T> revertRotateZ(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<T> rotatedSource = GeometricOperations.revertRotateZ(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<T> rotatedSink = GeometricOperations.revertRotateZ(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<>(rotatedSource, rotatedSink, lineToRotate.getType());
    }

    /**
     * Reverts the rotation of the {@link ILine4 Line} around the z-axis by the
     * provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ILine4 Line}.
     * 
     * @param lineToRotate
     *            the {@link ILine4 Line} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ILine4 Line}.
     */
    public static final <R extends Number> ILine4<R> revertRotateZ(
            final ILine4<?> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<R> rotatedSource = GeometricOperations.revertRotateZ(
                lineToRotate.getSource(), rotationAngle, returnType);

        IPoint4<R> rotatedSink = GeometricOperations.revertRotateZ(
                lineToRotate.getSink(), rotationAngle, returnType);

        return new Line4<>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Creates the translation {@link IMatrix4 Matrix} from the provided
     * {@link ITranslation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITranslation}.
     * 
     * @param translation
     *            the {@link ITranslation}.
     * @return the translation {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> translationMatrix(
            final ITranslation<T> translation) {

        return GeometricOperations.translationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation(), translation.getType());
    }

    /**
     * Creates the translation {@link IMatrix4 Matrix} from the provided
     * {@link ITranslation}.
     * 
     * @param <R>
     *            the {@link Number} type of the translation {@link IMatrix4
     *            Matrix}.
     * 
     * @param translation
     *            the {@link ITranslation}.
     * @param returnType
     *            the desired return type.
     * @return the translation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> translationMatrix(
            final ITranslation<?> translation, Class<? extends R> returnType) {

        return GeometricOperations.translationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation(), returnType);
    }


    /**
     * Creates the translation {@link IMatrix4 Matrix} from the provided
     * components.
     * 
     * @param <R>
     *            the {@link Number} type of the translation {@link IMatrix4
     *            Matrix}.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @param returnType
     *            the desired return type.
     * @return the translation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> translationMatrix(
            final Number tx, final Number ty, final Number tz,
            Class<? extends R> returnType) {


        return new Matrix4<>(Vector4.I_UNIT_VECTOR(returnType),
                Vector4.J_UNIT_VECTOR(returnType),
                Vector4.K_UNIT_VECTOR(returnType),
                new Point4<R>(tx, ty, tz, returnType), returnType);
    }

    /**
     * Creates the inverse translation {@link IMatrix4 Matrix} from the provided
     * {@link ITranslation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITranslation}.
     * 
     * @param translation
     *            the {@link ITranslation}.
     * @return the inverse translation {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> inverseTranslationMatrix(
            final ITranslation<T> translation) {

        return GeometricOperations.inverseTranslationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation(), translation.getType());
    }

    /**
     * Creates the inverse translation {@link IMatrix4 Matrix} from the provided
     * {@link ITranslation}.
     * 
     * @param <R>
     *            the {@link Number} type of the translation {@link IMatrix4
     *            Matrix}.
     * 
     * @param translation
     *            the {@link ITranslation}.
     * @param returnType
     *            the desired return type.
     * @return the inverse translation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseTranslationMatrix(
            final ITranslation<?> translation, Class<? extends R> returnType) {

        return GeometricOperations.inverseTranslationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation(), returnType);
    }


    /**
     * Creates the inverse translation {@link IMatrix4 Matrix} from the provided
     * components.
     * 
     * @param <R>
     *            the {@link Number} type of the translation {@link IMatrix4
     *            Matrix}.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @param returnType
     *            the desired return type.
     * @return the translation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseTranslationMatrix(
            final Number tx, final Number ty, final Number tz,
            Class<? extends R> returnType) {

        Number minusTx = ArithmeticOperations.subtract(0, tx);
        Number minusTy = ArithmeticOperations.subtract(0, ty);
        Number minusTz = ArithmeticOperations.subtract(0, tz);

        return GeometricOperations.translationMatrix(minusTx, minusTy, minusTz,
                returnType);
    }

    /**
     * Creates the scaling {@link IMatrix4 Matrix} from the provided
     * {@link IScaleFactor ScaleFactor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IScaleFactor
     *            ScaleFactor}.
     * 
     * @param scaling
     *            the {@link IScaleFactor ScaleFactor}.
     * @return the scaling {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> scalingMatrix(
            final IScaleFactor<T> scaling) {

        return GeometricOperations.scalingMatrix(scaling.getScaleFactorX(),
                scaling.getScaleFactorY(), scaling.getScaleFactorZ(),
                scaling.getType());
    }

    /**
     * Creates the scaling {@link IMatrix4 Matrix} from the provided
     * {@link IScaleFactor ScaleFactor}.
     * 
     * @param <R>
     *            the {@link Number} type of the scale {@link IMatrix4 Matrix}.
     * 
     * @param scaling
     *            {@link IScaleFactor ScaleFactor}.
     * @param returnType
     *            the desired return type.
     * @return the scaling {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> scalingMatrix(
            final IScaleFactor<?> scaling, final Class<? extends R> returnType) {

        return GeometricOperations.scalingMatrix(scaling.getScaleFactorX(),
                scaling.getScaleFactorY(), scaling.getScaleFactorZ(),
                returnType);
    }


    /**
     * Creates the scaling {@link IMatrix4 Matrix} from the provided components.
     * 
     * @param <R>
     *            the {@link Number} type of the scale {@link IMatrix4 Matrix}.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @param returnType
     *            the desired return type.
     * @return the scaling {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> scalingMatrix(
            final Number sx, final Number sy, final Number sz,
            final Class<? extends R> returnType) {

        List<Class<? extends Number>> classes = new ArrayList<>(3);
        classes.add(sx.getClass());
        classes.add(sy.getClass());
        classes.add(sz.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Matrix4<>(VectorAlgebraicOperations.multiply(
                Vector4.I_UNIT_VECTOR(highestPrecision), sx),
                VectorAlgebraicOperations.multiply(
                        Vector4.J_UNIT_VECTOR(highestPrecision), sy),
                VectorAlgebraicOperations.multiply(
                        Vector4.K_UNIT_VECTOR(highestPrecision), sz),
                Point4.ORIGIN(highestPrecision), returnType);
    }

    /**
     * Creates the inverse scaling {@link IMatrix4 Matrix} from the provided
     * {@link IScaleFactor ScaleFactor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IScaleFactor
     *            ScaleFactor}.
     * 
     * @param scaling
     *            the {@link IScaleFactor ScaleFactor}
     * @return the inverse scaling {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> inverseScalingMatrix(
            final IScaleFactor<T> scaling) {

        return GeometricOperations.inverseScalingMatrix(
                scaling.getScaleFactorX(), scaling.getScaleFactorY(),
                scaling.getScaleFactorZ(), scaling.getType());
    }

    /**
     * Creates the inverse scaling {@link IMatrix4 Matrix} from the provided
     * {@link IScaleFactor ScaleFactor}.
     * 
     * @param <R>
     *            the {@link Number} type of the scale {@link IMatrix4 Matrix}.
     * 
     * @param scaling
     *            the {@link IScaleFactor ScaleFactor}
     * @param returnType
     *            the desired return type.
     * @return the inverse scaling {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseScalingMatrix(
            final IScaleFactor<?> scaling, final Class<? extends R> returnType) {

        return GeometricOperations.inverseScalingMatrix(
                scaling.getScaleFactorX(), scaling.getScaleFactorY(),
                scaling.getScaleFactorZ(), returnType);
    }


    /**
     * Creates the inverse scaling {@link IMatrix4 Matrix} from the provided
     * components.
     * 
     * @param <R>
     *            the {@link Number} type of the scale {@link IMatrix4 Matrix}.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @param returnType
     *            the desired return type.
     * @return the scaling {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseScalingMatrix(
            final Number sx, final Number sy, final Number sz,
            final Class<? extends R> returnType) {

        return GeometricOperations.scalingMatrix(
                ArithmeticOperations.divide(1, sx),
                ArithmeticOperations.divide(1, sy),
                ArithmeticOperations.divide(1, sz), returnType);
    }



    /**
     * Creates the linear scaling {@link IMatrix4 Matrix} from the provided
     * component.
     * 
     * @param <R>
     *            the {@link Number} type of the scale {@link IMatrix4 Matrix}.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @param returnType
     *            the desired return type.
     * @return the linear scaling {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> linearScalingMatrix(
            final Number scalingFactor, final Class<? extends R> returnType) {


        return GeometricOperations.scalingMatrix(scalingFactor, scalingFactor,
                scalingFactor, returnType);
    }

    /**
     * Creates the inverse linear scaling {@link IMatrix4 Matrix} from the
     * provided component.
     * 
     * @param <R>
     *            the {@link Number} type of the scale {@link IMatrix4 Matrix}.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @param returnType
     *            the desired return type.
     * @return the inverse linear scaling {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseLinearScalingMatrix(
            final Number scalingFactor, final Class<? extends R> returnType) {


        return GeometricOperations.inverseScalingMatrix(scalingFactor,
                scalingFactor, scalingFactor, returnType);
    }


    /**
     * Creates the shear {@link IMatrix4 Matrix} from the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IShearFactor ShearFactor
     *            Factor}.
     * 
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}
     * @return the shear {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> shearMatrix(
            final IShearFactor<T> shearFactor) {

        return GeometricOperations.shearMatrix(
                shearFactor.getShearCoefficient(),
                shearFactor.getShearComponent(), shearFactor.getType());
    }


    /**
     * Creates the shear {@link IMatrix4 Matrix} from the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the shear {@link IMatrix4 Matrix}.
     * 
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @param returnType
     *            the desired return type.
     * @return the shear {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> shearMatrix(
            final IShearFactor<?> shearFactor,
            final Class<? extends R> returnType) {

        return GeometricOperations.shearMatrix(
                shearFactor.getShearCoefficient(),
                shearFactor.getShearComponent(), returnType);
    }



    /**
     * Creates the shear {@link IMatrix4 Matrix} for shearing by the provided
     * shear coefficient and shear component.
     * 
     * @param <R>
     *            the {@link Number} type of the shear {@link IMatrix4 Matrix}.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the component to shear by.
     * @param returnType
     *            the desired return type.
     * @return the shear {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> shearMatrix(
            final Number shearCoefficient, final ShearComponent shearComponent,
            Class<? extends R> returnType) {

        IVector4<? extends Number> i;
        IVector4<? extends Number> j;
        IVector4<? extends Number> k;

        switch (shearComponent) {
            case X_BY_Y:
                i = Vector4.I_UNIT_VECTOR(returnType);
                j = new Vector4<R>(shearCoefficient, 1, 0, returnType);
                k = Vector4.K_UNIT_VECTOR(returnType);
                break;
            case X_BY_Z:
                i = Vector4.I_UNIT_VECTOR(returnType);
                j = Vector4.J_UNIT_VECTOR(returnType);
                k = new Vector4<R>(shearCoefficient, 0, 1, returnType);
                break;
            case Y_BY_X:
                i = new Vector4<R>(1, shearCoefficient, 0, returnType);
                j = Vector4.J_UNIT_VECTOR(returnType);
                k = Vector4.K_UNIT_VECTOR(returnType);
                break;
            case Y_BY_Z:
                i = Vector4.I_UNIT_VECTOR(returnType);
                j = Vector4.J_UNIT_VECTOR(returnType);
                k = new Vector4<R>(0, shearCoefficient, 1, returnType);
                break;
            case Z_BY_X:
                i = new Vector4<R>(1, 0, shearCoefficient, returnType);
                j = Vector4.J_UNIT_VECTOR(returnType);
                k = Vector4.K_UNIT_VECTOR(returnType);
                break;
            case Z_BY_Y:
                i = Vector4.I_UNIT_VECTOR(returnType);
                j = new Vector4<R>(0, 1, shearCoefficient, returnType);
                k = Vector4.K_UNIT_VECTOR(returnType);
                break;
            default:
                throw new IllegalArgumentException(
                        "Cannot shear by provided component!");
        }

        return new Matrix4<>(i, j, k, Point4.ORIGIN(returnType), returnType);
    }


    /**
     * Creates the inverse shear {@link IMatrix4 Matrix} for the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IShearFactor ShearFactor
     *            Factor}.
     * 
     * @param shearFactor
     *            the {@link IShearFactor ShearFactor Factor}.
     * @return the inverse shear {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> inverseShearMatrix(
            final IShearFactor<T> shearFactor) {

        return GeometricOperations.inverseShearMatrix(
                shearFactor.getShearCoefficient(),
                shearFactor.getShearComponent(), shearFactor.getType());
    }

    /**
     * Creates the inverse shear {@link IMatrix4 Matrix} for the provided
     * {@link IShearFactor ShearFactor Factor}.
     * 
     * @param <R>
     *            the {@link Number} type of the shear {@link IMatrix4 Matrix}.
     * 
     * @param shear
     *            the {@link IShearFactor ShearFactor Factor}.
     * @param returnType
     *            the desired return type.
     * @return the inverse shear {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseShearMatrix(
            final IShearFactor<?> shear, final Class<? extends R> returnType) {

        return GeometricOperations.inverseShearMatrix(
                shear.getShearCoefficient(), shear.getShearComponent(),
                returnType);
    }


    /**
     * Creates the inverse shear {@link IMatrix4 Matrix} for shearing by the
     * provided shear coefficient and shear component.
     * 
     * @param <R>
     *            the {@link Number} type of the shear {@link IMatrix4 Matrix}.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the component to shear by.
     * @param returnType
     *            the desired return type.
     * @return the inverse shear {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseShearMatrix(
            final Number shearCoefficient, final ShearComponent shearComponent,
            final Class<? extends R> returnType) {

        return GeometricOperations.shearMatrix(
                ArithmeticOperations.subtract(0, shearCoefficient),
                shearComponent, returnType);
    }

    /**
     * Creates the rotation {@link IMatrix4 Matrix} for the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IRotation Angle-Axis
     *            Rotation}.
     * 
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> rotationMatrix(
            final IRotation<T> rotation) {

        return GeometricOperations.rotationMatrix(rotation.getRotationAxis(),
                rotation.getRotationAngle(), rotation.getType());
    }

    /**
     * Creates the rotation {@link IMatrix4 Matrix} for the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> rotationMatrix(
            final IRotation<?> rotation, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(rotation.getRotationAxis(),
                rotation.getRotationAngle(), returnType);
    }

    /**
     * Creates the rotation {@link IMatrix4 Matrix} for a rotation around the
     * provided rotation {@link IVector4 Axis} by the provided angle.
     * 
     * @param <T>
     *            the {@link Number} type of the rotation {@link IVector4 Axis}.
     * 
     * @param rotationAxis
     *            the {@link IVector4 Axis} of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> rotationMatrix(
            final ITuple4<T> rotationAxis, final T rotationAngle) {

        Double cos = Math.cos(NumberUtils.cast(rotationAngle, Double.class));
        Double sin = Math.sin(NumberUtils.cast(rotationAngle, Double.class));

        Double oneMinusCos = 1.0 - cos;

        T ux = rotationAxis.getX();
        T uy = rotationAxis.getY();
        T uz = rotationAxis.getZ();

        Number ux_2 = ArithmeticOperations.multiply(ux, ux);
        Number uy_2 = ArithmeticOperations.multiply(uy, uy);
        Number uz_2 = ArithmeticOperations.multiply(uz, uz);

        Number uy_ux = ArithmeticOperations.multiply(uy, ux);
        Number uz_ux = ArithmeticOperations.multiply(uz, ux);
        Number uy_uz = ArithmeticOperations.multiply(uy, uz);

        Number ux_sin = ArithmeticOperations.multiply(ux, sin);
        Number uy_sin = ArithmeticOperations.multiply(uy, sin);
        Number uz_sin = ArithmeticOperations.multiply(uz, sin);


        Number r_xi = ArithmeticOperations.add(
                ArithmeticOperations.multiply(oneMinusCos, ux_2), cos);
        Number r_yi = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uy_ux, oneMinusCos), uz_sin);
        Number r_zi = ArithmeticOperations.subtract(
                ArithmeticOperations.multiply(uz_ux, oneMinusCos), uy_sin);

        Number r_xj = ArithmeticOperations.subtract(
                ArithmeticOperations.multiply(uy_ux, oneMinusCos), uz_sin);
        Number r_yj = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uy_2, oneMinusCos), cos);
        Number r_zj = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uy_uz, oneMinusCos), ux_sin);

        Number r_xk = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uz_ux, oneMinusCos), uy_sin);
        Number r_yk = ArithmeticOperations.subtract(
                ArithmeticOperations.multiply(uy_uz, oneMinusCos), ux_sin);
        Number r_zk = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uz_2, oneMinusCos), cos);


        Class<? extends Number> highestPrecisionI = NumberComparator
                .greaterPrecisionType(r_xi, r_yi, r_zi);
        IVector4<? extends Number> i = new Vector4<Number>(r_xi, r_yi, r_zi,
                highestPrecisionI);

        Class<? extends Number> highestPrecisionJ = NumberComparator
                .greaterPrecisionType(r_xj, r_yj, r_zj);
        IVector4<? extends Number> j = new Vector4<Number>(r_xj, r_yj, r_zj,
                highestPrecisionJ);

        Class<? extends Number> highestPrecisionK = NumberComparator
                .greaterPrecisionType(r_xk, r_yk, r_zk);
        IVector4<? extends Number> k = new Vector4<Number>(r_xk, r_yk, r_zk,
                highestPrecisionK);


        return new Matrix4<>(i, j, k, Point4.ORIGIN(rotationAxis.getType()),
                rotationAxis.getType());
    }

    /**
     * Creates the rotation {@link IMatrix4 Matrix} for a rotation around the
     * provided rotation {@link IVector4 Axis} by the provided angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAxis
     *            the {@link IVector4 Axis} of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> rotationMatrix(
            final ITuple4<?> rotationAxis, final Number rotationAngle,
            final Class<? extends R> returnType) {

        Double cos = Math.cos(NumberUtils.cast(rotationAngle, Double.class));
        Double sin = Math.sin(NumberUtils.cast(rotationAngle, Double.class));

        Double oneMinusCos = 1.0 - cos;

        Number ux = rotationAxis.getX();
        Number uy = rotationAxis.getY();
        Number uz = rotationAxis.getZ();

        Number ux_2 = ArithmeticOperations.multiply(ux, ux);
        Number uy_2 = ArithmeticOperations.multiply(uy, uy);
        Number uz_2 = ArithmeticOperations.multiply(uz, uz);

        Number uy_ux = ArithmeticOperations.multiply(uy, ux);
        Number uz_ux = ArithmeticOperations.multiply(uz, ux);
        Number uy_uz = ArithmeticOperations.multiply(uy, uz);

        Number ux_sin = ArithmeticOperations.multiply(ux, sin);
        Number uy_sin = ArithmeticOperations.multiply(uy, sin);
        Number uz_sin = ArithmeticOperations.multiply(uz, sin);


        Number r_xi = ArithmeticOperations.add(
                ArithmeticOperations.multiply(oneMinusCos, ux_2), cos);
        Number r_yi = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uy_ux, oneMinusCos), uz_sin);
        Number r_zi = ArithmeticOperations.subtract(
                ArithmeticOperations.multiply(uz_ux, oneMinusCos), uy_sin);

        Number r_xj = ArithmeticOperations.subtract(
                ArithmeticOperations.multiply(uy_ux, oneMinusCos), uz_sin);
        Number r_yj = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uy_2, oneMinusCos), cos);
        Number r_zj = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uy_uz, oneMinusCos), ux_sin);

        Number r_xk = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uz_ux, oneMinusCos), uy_sin);
        Number r_yk = ArithmeticOperations.subtract(
                ArithmeticOperations.multiply(uy_uz, oneMinusCos), ux_sin);
        Number r_zk = ArithmeticOperations.add(
                ArithmeticOperations.multiply(uz_2, oneMinusCos), cos);

        Class<? extends Number> highestPrecisionI = NumberComparator
                .greaterPrecisionType(r_xi, r_yi, r_zi);
        IVector4<? extends Number> i = new Vector4<Number>(r_xi, r_yi, r_zi,
                highestPrecisionI);

        Class<? extends Number> highestPrecisionJ = NumberComparator
                .greaterPrecisionType(r_xj, r_yj, r_zj);
        IVector4<? extends Number> j = new Vector4<Number>(r_xj, r_yj, r_zj,
                highestPrecisionJ);

        Class<? extends Number> highestPrecisionK = NumberComparator
                .greaterPrecisionType(r_xk, r_yk, r_zk);
        IVector4<? extends Number> k = new Vector4<Number>(r_xk, r_yk, r_zk,
                highestPrecisionK);

        return new Matrix4<>(i, j, k, Point4.ORIGIN(returnType), returnType);
    }

    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} for the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IRotation Angle-Axis
     *            Rotation}.
     * 
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> inverseRotationMatrix(
            final IRotation<T> rotation) {

        return GeometricOperations.inverseRotationMatrix(
                rotation.getRotationAxis(), rotation.getRotationAngle(),
                rotation.getType());
    }

    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} for the provided
     * {@link IRotation Angle-Axis Rotation}.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotation
     *            the {@link IRotation Angle-Axis Rotation}.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseRotationMatrix(
            final IRotation<?> rotation, final Class<? extends R> returnType) {

        return GeometricOperations.inverseRotationMatrix(
                rotation.getRotationAxis(), rotation.getRotationAngle(),
                returnType);
    }

    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} for a rotation
     * around the provided rotation {@link IVector4 Axis} by the provided angle.
     * 
     * @param <T>
     *            the {@link Number} type of the rotation {@link IVector4 Axis}
     *            and angle.
     * 
     * @param rotationAxis
     *            the {@link IVector4 Axis} of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <T extends Number> IMatrix4<T> inverseRotationMatrix(
            final IVector4<T> rotationAxis, final T rotationAngle) {

        return GeometricOperations.rotationMatrix(rotationAxis, rotationAngle)
                .transpose();
    }

    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} for a rotation
     * around the provided rotation {@link IVector4 Axis} by the provided angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAxis
     *            the {@link IVector4 Axis} of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseRotationMatrix(
            final IVector4<?> rotationAxis, final Number rotationAngle,
            final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(rotationAxis, rotationAngle,
                returnType).transpose();
    }


    /**
     * Creates the rotation {@link IMatrix4 Matrix} around the x-axis by the
     * provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> xRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.I_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType);
    }



    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} around the x-axis by
     * the provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseXRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.I_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType).transpose();
    }



    /**
     * Creates the rotation {@link IMatrix4 Matrix} around the y-axis by the
     * provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> yRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.J_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType);
    }



    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} around the y-axis by
     * the provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseYRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.J_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType).transpose();
    }


    /**
     * Creates the rotation {@link IMatrix4 Matrix} around the z-axis by the
     * provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> zRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.K_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType);
    }


    /**
     * Creates the inverse rotation {@link IMatrix4 Matrix} around the z-axis by
     * the provided rotation angle.
     * 
     * @param <R>
     *            the {@link Number} type of the rotation {@link IMatrix4
     *            Matrix}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> inverseZRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.K_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType).transpose();
    }


    /**
     * Translates the {@link ITuple4 Tuple} by the x, y, and z translation.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            translate.
     * 
     * @param tupleToTranslate
     *            the {@link ITuple4 Tuple} to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @return the translated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> translate(
            final ITuple4<T> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation) {

        IMatrix4<T> translationMatrix = GeometricOperations.translationMatrix(
                xTranslation, yTranslation, zTranslation,
                tupleToTranslate.getType());

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate, tupleToTranslate.getType());
    }

    /**
     * Translates the {@link ITuple4 Tuple} by the x, y, and z translation.
     * 
     * @param <R>
     *            the {@link Number} type of the translated {@link ITuple4
     *            Tuple}.
     * 
     * @param tupleToTranslate
     *            the {@link ITuple4 Tuple} to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @param returnType
     *            the desired return type.
     * @return the translated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> translate(
            final ITuple4<?> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation,
            final Class<? extends R> returnType) {

        IMatrix4<R> translationMatrix = GeometricOperations.translationMatrix(
                xTranslation, yTranslation, zTranslation, returnType);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate, returnType);
    }


    /**
     * Reverts the translation of the {@link ITuple4 Tuple} by the x, y, and z
     * translation.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            translate.
     * 
     * @param tupleToTranslate
     *            the {@link ITuple4 Tuple} to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @return the translated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertTranslate(
            final ITuple4<T> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation) {

        IMatrix4<T> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(xTranslation, yTranslation,
                        zTranslation, tupleToTranslate.getType());

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate, tupleToTranslate.getType());
    }

    /**
     * Reverts the translation of the {@link ITuple4 Tuple} by the x, y, and z
     * translation.
     * 
     * @param <R>
     *            the {@link Number} type of the translated {@link ITuple4
     *            Tuple}.
     * 
     * @param tupleToTranslate
     *            the {@link ITuple4 Tuple} to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @param returnType
     *            the desired return type.
     * @return the translated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertTranslate(
            final ITuple4<?> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation,
            Class<? extends R> returnType) {

        IMatrix4<R> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(xTranslation, yTranslation,
                        zTranslation, returnType);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate, returnType);
    }


    /**
     * Scales the {@link ITuple4 Tuple} by the x, y, and z scaling factor.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to scale.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @return the scaled {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> scale(
            final ITuple4<T> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor) {

        IMatrix4<T> scaleMatrix = GeometricOperations.scalingMatrix(
                xScalingFactor, yScalingFactor, zScalingFactor,
                tupleToScale.getType());

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale,
                tupleToScale.getType());
    }

    /**
     * Scales the {@link ITuple4 Tuple} by the x, y, and z scaling factor.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ITuple4 Tuple}.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> scale(
            final ITuple4<?> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scaleMatrix = GeometricOperations.scalingMatrix(
                xScalingFactor, yScalingFactor, zScalingFactor, returnType);

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale,
                returnType);
    }


    /**
     * Reverts the scaling of the {@link ITuple4 Tuple} by the x, y, and z
     * scaling factor.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to scale.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @return the scaled {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertScale(
            final ITuple4<T> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor) {

        IMatrix4<T> scaleMatrix = GeometricOperations.inverseScalingMatrix(
                xScalingFactor, yScalingFactor, zScalingFactor,
                tupleToScale.getType());

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale,
                tupleToScale.getType());
    }

    /**
     * Reverts the scaling of the {@link ITuple4 Tuple} by the x, y, and z
     * scaling factor.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ITuple4 Tuple}.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertScale(
            final ITuple4<?> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scaleMatrix = GeometricOperations.inverseScalingMatrix(
                xScalingFactor, yScalingFactor, zScalingFactor, returnType);

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale,
                returnType);
    }


    /**
     * Uniformly scales the {@link ITuple4 Tuple} by the scaling factor.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to scale.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the uniformly scaled {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> linearScale(
            final ITuple4<T> tupleToScale, final Number scalingFactor) {

        return GeometricOperations.scale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor);
    }

    /**
     * Uniformly scales the {@link ITuple4 Tuple} by the scaling factor.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ITuple4 Tuple}.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the uniformly scaled {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> linearScale(
            final ITuple4<?> tupleToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        return GeometricOperations.scale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor, returnType);
    }


    /**
     * Reverts the uniform scaling of the {@link ITuple4 Tuple} by the scaling
     * factor.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to scale.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the uniformly scaled {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertLinearScale(
            final ITuple4<T> tupleToScale, final Number scalingFactor) {

        return GeometricOperations.revertScale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor);
    }

    /**
     * Reverts the uniform scaling of the {@link ITuple4 Tuple} by the scaling
     * factor.
     * 
     * @param <R>
     *            the {@link Number} type of the scaled {@link ITuple4 Tuple}.
     * 
     * @param tupleToScale
     *            the {@link ITuple4 Tuple} to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the uniformly scaled {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertLinearScale(
            final ITuple4<?> tupleToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        return GeometricOperations.revertScale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor, returnType);
    }


    /**
     * Shears the {@link ITuple4 Tuple} by the provided shear coefficient and
     * the shear component.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to shear.
     * 
     * @param tupleToShear
     *            the {@link ITuple4 Tuple} to shear.
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the shear component
     * @return the sheared {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> shear(
            final ITuple4<T> tupleToShear, final Number shearCoefficient,
            final ShearComponent shearComponent) {

        IMatrix4<T> shearMatrix = GeometricOperations.shearMatrix(
                shearCoefficient, shearComponent, tupleToShear.getType());


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear,
                tupleToShear.getType());
    }

    /**
     * Shears the {@link ITuple4 Tuple} by the provided shear coefficient and
     * the shear component.
     * 
     * @param <R>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to shear.
     * 
     * @param tupleToShear
     *            the {@link ITuple4 Tuple} to shear.
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the shear component
     * @param returnType
     *            the desired return type.
     * @return the sheared {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> shear(
            final ITuple4<?> tupleToShear, final Number shearCoefficient,
            final ShearComponent shearComponent,
            final Class<? extends R> returnType) {

        IMatrix4<R> shearMatrix = GeometricOperations.shearMatrix(
                shearCoefficient, shearComponent, returnType);


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear,
                returnType);
    }


    /**
     * Reverts the shear of the {@link ITuple4 Tuple} by the provided shear
     * coefficient and the shear component.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to shear.
     * 
     * @param tupleToShear
     *            the {@link ITuple4 Tuple} to shear.
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the shear component
     * @return the sheared {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertShear(
            final ITuple4<T> tupleToShear, final Number shearCoefficient,
            final ShearComponent shearComponent) {

        IMatrix4<T> shearMatrix = GeometricOperations.inverseShearMatrix(
                shearCoefficient, shearComponent, tupleToShear.getType());


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear,
                tupleToShear.getType());
    }

    /**
     * Reverts the shear of the {@link ITuple4 Tuple} by the provided shear
     * coefficient and the shear component.
     * 
     * @param <R>
     *            the {@link Number} type of the sheared {@link ITuple4 Tuple}.
     * 
     * @param tupleToShear
     *            the {@link ITuple4 Tuple} to shear.
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the shear component
     * @param returnType
     *            the desired return type.
     * @return the sheared {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertShear(
            final ITuple4<?> tupleToShear, final Number shearCoefficient,
            final ShearComponent shearComponent,
            final Class<? extends R> returnType) {

        IMatrix4<R> shearMatrix = GeometricOperations.inverseShearMatrix(
                shearCoefficient, shearComponent, returnType);


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear,
                returnType);
    }


    /**
     * Rotates the {@link ITuple4 Tuple} around the {@link IVector4 Rotation
     * Axis} by the rotation angle .
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAxis
     *            the {@link IVector4 Rotation Axis}.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> rotate(
            final ITuple4<T> tupleToRotate, final IVector4<?> rotationAxis,
            final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations.rotationMatrix(
                rotationAxis, rotationAngle, tupleToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                tupleToRotate, tupleToRotate.getType());
    }

    /**
     * Rotates the {@link ITuple4 Tuple} around the {@link IVector4 Rotation
     * Axis} by the rotation angle .
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAxis
     *            the {@link IVector4 Rotation Axis}.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> rotate(
            final ITuple4<?> tupleToRotate, final IVector4<?> rotationAxis,
            final Number rotationAngle, final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.rotationMatrix(
                rotationAxis, rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                tupleToRotate, returnType);
    }


    /**
     * Reverses the rotation of the {@link ITuple4 Tuple} around the
     * {@link IVector4 Rotation Axis} by the rotation angle .
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAxis
     *            the {@link IVector4 Rotation Axis}.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertRotate(
            final ITuple4<T> tupleToRotate, final IVector4<?> rotationAxis,
            final Number rotationAngle) {

        IMatrix4<T> rotationMatrix = GeometricOperations.inverseRotationMatrix(
                rotationAxis, rotationAngle, tupleToRotate.getType());

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                tupleToRotate, tupleToRotate.getType());
    }

    /**
     * Reverses the rotation of the {@link ITuple4 Tuple} around the
     * {@link IVector4 Rotation Axis} by the rotation angle .
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAxis
     *            the {@link IVector4 Rotation Axis}.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertRotate(
            final ITuple4<?> tupleToRotate, final IVector4<?> rotationAxis,
            final Number rotationAngle, final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.inverseRotationMatrix(
                rotationAxis, rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                tupleToRotate, returnType);
    }


    /**
     * Rotates the {@link ITuple4 Tuple} by the provided rotation angle around
     * the x-axis.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> rotateX(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(tupleToRotate.getType()), rotationAngle);
    }

    /**
     * Rotates the {@link ITuple4 Tuple} by the provided rotation angle around
     * the x-axis.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> rotateX(
            final ITuple4<?> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        return GeometricOperations.rotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(returnType), rotationAngle, returnType);
    }


    /**
     * Reverts rotation of the {@link ITuple4 Tuple} by the provided rotation
     * angle around the x-axis.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertRotateX(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(tupleToRotate.getType()), rotationAngle);
    }

    /**
     * Reverts rotation of the {@link ITuple4 Tuple} by the provided rotation
     * angle around the x-axis.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertRotateX(
            final ITuple4<?> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(returnType), rotationAngle, returnType);
    }



    /**
     * Rotates the {@link ITuple4 Tuple} by the provided rotation angle around
     * the y-axis.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> rotateY(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(tupleToRotate.getType()), rotationAngle);
    }

    /**
     * Rotates the {@link ITuple4 Tuple} by the provided rotation angle around
     * the y-axis.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> rotateY(
            final ITuple4<?> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        return GeometricOperations.rotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(returnType), rotationAngle, returnType);
    }


    /**
     * Reverts rotation of the {@link ITuple4 Tuple} by the provided rotation
     * angle around the y-axis.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertRotateY(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(tupleToRotate.getType()), rotationAngle);
    }

    /**
     * Reverts rotation of the {@link ITuple4 Tuple} by the provided rotation
     * angle around the y-axis.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertRotateY(
            final ITuple4<?> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(returnType), rotationAngle, returnType);
    }



    /**
     * Rotates the {@link ITuple4 Tuple} by the provided rotation angle around
     * the z-axis.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> rotateZ(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(tupleToRotate.getType()), rotationAngle);
    }

    /**
     * Rotates the {@link ITuple4 Tuple} by the provided rotation angle around
     * the z-axis.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> rotateZ(
            final ITuple4<?> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        return GeometricOperations.rotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(returnType), rotationAngle, returnType);
    }


    /**
     * Reverts rotation of the {@link ITuple4 Tuple} by the provided rotation
     * angle around the z-axis.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link ITuple4 Tuple} to
     *            rotate.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <T extends Number> ITuple4<T> revertRotateZ(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(tupleToRotate.getType()), rotationAngle);
    }

    /**
     * Reverts rotation of the {@link ITuple4 Tuple} by the provided rotation
     * angle around the z-axis.
     * 
     * @param <R>
     *            the {@link Number} type of the rotated {@link ITuple4 Tuple}.
     * 
     * @param tupleToRotate
     *            the {@link ITuple4 Tuple} to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> revertRotateZ(
            final ITuple4<?> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(returnType), rotationAngle, returnType);
    }



    /**
     * Calculates the {@link IRotation Angle-Axis Rotation} by projecting the
     * first {@link IVector4 Vector} onto the second {@link IVector4 Vector}.
     * 
     * @param <R>
     *            the {@link Number} type of the {@link IRotation Angle-Axis
     *            Rotation}.
     * @param firstVector
     *            The {@link IVector4 Vector} to be projected.
     * @param secondVector
     *            The {@link IVector4 Vector} to be projected on.
     * @param returnType
     *            the desired return type.
     * @return The {@link IRotation Angle-Axis Rotation}.
     */
    public static final <R extends Number> IRotation<R> getRotationBetweenVectors(
            final IVector4<?> firstVector, final IVector4<?> secondVector,
            final Class<? extends R> returnType) {

        IVector4<?> normalizedFirstVector = firstVector.normalizedVector();
        IVector4<?> normalizedSecondVector = secondVector.normalizedVector();


        Double angle = Math.acos(Vector4.dotProduct(normalizedFirstVector,
                normalizedSecondVector, Double.class));

        IVector4<? extends Number> axis = Vector4.crossProduct(
                normalizedFirstVector, normalizedSecondVector, Double.class);

        return new Rotation<>(axis.getX(), axis.getY(), axis.getZ(), angle,
                returnType);
    }
}
