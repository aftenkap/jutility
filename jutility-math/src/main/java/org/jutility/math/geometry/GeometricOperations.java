package org.jutility.math.geometry;



import java.util.ArrayList;
import java.util.List;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.arithmetics.ArithmeticOperations;
import org.jutility.math.vectorAlgebra.IMatrix4;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.ITuple4;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Matrix4;
import org.jutility.math.vectorAlgebra.Point4;
import org.jutility.math.vectorAlgebra.Vector4;
import org.jutility.math.vectorAlgebra.VectorAlgebraicOperations;



/**
 * The {@link GeometricOperations} class provides a convenience wrapper around
 * the {@link VectorAlgebraicOperations} class.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class GeometricOperations {


    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The vector.
     * @param rhs
     *            The scalar.
     * @return The scaled point.
     */
    public static <T extends Number> IVector4<? extends Number> scale(
            final IVector4<T> lhs, final Number rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs);
    }

    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The vector.
     * @param rhs
     *            The scalar.
     * @param returnType
     *            the desired return type.
     * @return The scaled point.
     */
    public static <T extends Number, R extends Number> IVector4<R> scale(
            final IVector4<T> lhs, final Number rhs,
            Class<? extends R> returnType) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, returnType);
    }


    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @return The scaled point.
     */
    public static <T extends Number> IVector4<? extends Number> scale(
            final Number lhs, final IVector4<T> rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs);
    }

    /**
     * Scales the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @param returnType
     *            the desired return type.
     * @return The scaled point.
     */
    public static <T extends Number, R extends Number> IVector4<R> scale(
            final Number lhs, final IVector4<T> rhs,
            Class<? extends R> returnType) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, returnType);
    }


    /**
     * Translates the point by the translation.
     * 
     * @param pointToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @return the translated point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> translate(
            final IPoint4<T> pointToTranslate, final ITranslation<S> translation) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .translationMatrix(translation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate);
    }

    /**
     * Translates the point by the translation.
     * 
     * @param pointToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @param returnType
     *            the desired return type.
     * @return the translated point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> translate(
            final IPoint4<T> pointToTranslate,
            final ITranslation<S> translation, Class<? extends R> returnType) {

        IMatrix4<R> translationMatrix = GeometricOperations.translationMatrix(
                translation, returnType);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate, returnType);
    }


    /**
     * Reverts the translation of the point by the translation.
     * 
     * @param pointToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @return the translated point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> revertTranslate(
            final IPoint4<T> pointToTranslate, final ITranslation<S> translation) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(translation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate);
    }

    /**
     * Reverts the translation of the point by the translation.
     * 
     * @param pointToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @param returnType
     *            the desired return type.
     * @return the translated point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> revertTranslate(
            final IPoint4<T> pointToTranslate,
            final ITranslation<S> translation, Class<? extends R> returnType) {

        IMatrix4<R> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(translation, returnType);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                pointToTranslate, returnType);
    }


    /**
     * Scales the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> scale(
            final IPoint4<T> pointToScale, final IScale<S> scalingFactor) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .scalingMatrix(scalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale);
    }

    /**
     * Scales the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> scale(
            final IPoint4<T> pointToScale, final IScale<S> scalingFactor,
            Class<? extends R> returnType) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .scalingMatrix(scalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale,
                returnType);
    }


    /**
     * Reverts the scaling of the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> revertScale(
            final IPoint4<T> pointToScale, final IScale<S> scalingFactor) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .inverseScalingMatrix(scalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale);
    }

    /**
     * Reverts the scaling of the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> revertScale(
            final IPoint4<T> pointToScale, final IScale<S> scalingFactor,
            Class<? extends R> returnType) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .inverseScalingMatrix(scalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale,
                returnType);
    }


    /**
     * Uniformly scales the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled point.
     */
    public static <T extends Number> IPoint4<? extends Number> linearScale(
            final IPoint4<T> pointToScale, final Number scalingFactor) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .linearScalingMatrix(scalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale);
    }

    /**
     * Uniformly scales the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled point.
     */
    public static <T extends Number, R extends Number> IPoint4<R> linearScale(
            final IPoint4<T> pointToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scaleMatrix = GeometricOperations.linearScalingMatrix(
                scalingFactor, returnType);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale,
                returnType);
    }


    /**
     * Reverts the uniform scaling of the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled point.
     */
    public static <T extends Number> IPoint4<? extends Number> revertLinearScale(
            final IPoint4<T> pointToScale, final Number scalingFactor) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .inverseLinearScalingMatrix(scalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale);
    }

    /**
     * Reverts the uniform scaling of the point by the scaling factor.
     * 
     * @param pointToScale
     *            the point to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled point.
     */
    public static <T extends Number, R extends Number> IPoint4<R> revertLinearScale(
            final IPoint4<T> pointToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<R> scaleMatrix = GeometricOperations
                .inverseLinearScalingMatrix(scalingFactor, returnType);

        return VectorAlgebraicOperations.multiply(scaleMatrix, pointToScale,
                returnType);
    }


    /**
     * Shears the point by the provided shear factor.
     * 
     * @param pointToShear
     *            the point to shear.
     * @param shear
     *            the shear.
     * @return the sheared point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> shear(
            final IPoint4<T> pointToShear, final IShear<S> shear) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .shearMatrix(shear);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear);
    }

    /**
     * Shears the point by the provided shear factor.
     * 
     * @param pointToShear
     *            the point to shear.
     * @param shear
     *            the shear.
     * @param returnType
     *            the desired return type.
     * @return the sheared point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> shear(
            final IPoint4<T> pointToShear, final IShear<S> shear,
            final Class<? extends R> returnType) {

        IMatrix4<R> shearMatrix = GeometricOperations.shearMatrix(shear,
                returnType);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear,
                returnType);
    }


    /**
     * Reverts the shear of the point by the provided shear factor.
     * 
     * @param pointToShear
     *            the point to shear.
     * @param shear
     *            the shear.
     * @return the sheared point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> revertShear(
            final IPoint4<T> pointToShear, final IShear<S> shear) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .inverseShearMatrix(shear);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear);
    }

    /**
     * Reverts the shear of the point by the provided shear factor.
     * 
     * @param pointToShear
     *            the point to shear.
     * @param shear
     *            the shear.
     * @param returnType
     *            the desired return type.
     * @return the sheared point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> revertShear(
            final IPoint4<T> pointToShear, final IShear<S> shear,
            Class<? extends R> returnType) {

        IMatrix4<R> shearMatrix = GeometricOperations.inverseShearMatrix(shear,
                returnType);

        return VectorAlgebraicOperations.multiply(shearMatrix, pointToShear,
                returnType);
    }


    /**
     * Rotates the point around by the rotation angle around the rotation axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> rotate(
            final IPoint4<T> pointToRotate, final IRotation<S> rotation) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .rotationMatrix(rotation);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Rotates the point around by the rotation angle around the rotation axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotation
     *            the rotation.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> rotate(
            final IPoint4<T> pointToRotate, final IRotation<S> rotation,
            Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.rotationMatrix(
                rotation, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * rotation axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> revertRotate(
            final IPoint4<T> pointToRotate, final IRotation<S> rotation) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseRotationMatrix(rotation);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * rotation axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotation
     *            the rotation.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> revertRotate(
            final IPoint4<T> pointToRotate, final IRotation<S> rotation,
            final Class<R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.inverseRotationMatrix(
                rotation, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }

    /**
     * Rotates the point around by the rotation angle around the x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> rotateX(
            final IPoint4<T> pointToRotate, final S rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .xRotationMatrix(rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Rotates the point around by the rotation angle around the x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type
     * @return the rotated point.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> rotateX(
            final IPoint4<T> pointToRotate, final S rotationAngle,
            Class<R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.xRotationMatrix(
                rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static <T extends Number> IPoint4<? extends Number> revertRotateX(
            final IPoint4<T> pointToRotate, Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseXRotationMatrix(rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, R extends Number> IPoint4<R> revertRotateX(
            final IPoint4<T> pointToRotate, Number rotationAngle,
            final Class<R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations
                .inverseXRotationMatrix(rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Rotates the point around by the rotation angle around the y-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static <T extends Number> IPoint4<? extends Number> rotateY(
            final IPoint4<T> pointToRotate, Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .yRotationMatrix(rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Rotates the point around by the rotation angle around the y-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     */
    public static <T extends Number, R extends Number> IPoint4<R> rotateY(
            final IPoint4<T> pointToRotate, Number rotationAngle,
            Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.yRotationMatrix(
                rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static <T extends Number> IPoint4<? extends Number> revertRotateY(
            final IPoint4<T> pointToRotate, Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseYRotationMatrix(rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     * 
     */
    public static <T extends Number, R extends Number> IPoint4<R> revertRotateY(
            final IPoint4<T> pointToRotate, Number rotationAngle,
            final Class<R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations
                .inverseYRotationMatrix(rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Rotates the point around by the rotation angle around the z-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static <T extends Number> IPoint4<? extends Number> rotateZ(
            final IPoint4<T> pointToRotate, Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .zRotationMatrix(rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Rotates the point around by the rotation angle around the z-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     */
    public static <T extends Number, R extends Number> IPoint4<R> rotateZ(
            final IPoint4<T> pointToRotate, Number rotationAngle,
            final Class<? extends R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations.zRotationMatrix(
                rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }

    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated point.
     * 
     */
    public static <T extends Number> IPoint4<? extends Number> revertRotateZ(
            final IPoint4<T> pointToRotate, Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseZRotationMatrix(rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, pointToRotate);
    }

    /**
     * Reverts the rotation of the point around by the rotation angle around the
     * x-axis.
     * 
     * @param pointToRotate
     *            the point to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated point.
     */
    public static <T extends Number, R extends Number> IPoint4<R> revertRotateZ(
            final IPoint4<T> pointToRotate, Number rotationAngle,
            final Class<R> returnType) {

        IMatrix4<R> rotationMatrix = GeometricOperations
                .inverseZRotationMatrix(rotationAngle, returnType);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                pointToRotate, returnType);
    }


    /**
     * Translates the line by the translation.
     * 
     * @param lineToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @return the translated line.
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> translate(
            final ILine4<T> lineToTranslate, final Translation<S> translation) {


        IPoint4<? extends Number> translatedSource = GeometricOperations
                .translate(lineToTranslate.getSource(), translation);

        IPoint4<? extends Number> translatedSink = GeometricOperations
                .translate(lineToTranslate.getSink(), translation);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(translatedSource.getType());
        classes.add(translatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(translatedSource, translatedSink,
                highestPrecision);
    }

    /**
     * Translates the line by the translation.
     * 
     * @param lineToTranslate
     *            the point to translate.
     * @param translation
     *            the translation.
     * @param returnType
     *            the desired return type.
     * @return the translated line.
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> translate(
            final ILine4<T> lineToTranslate, final Translation<S> translation,
            final Class<? extends R> returnType) {


        IPoint4<R> translatedSource = GeometricOperations.translate(
                lineToTranslate.getSource(), translation, returnType);

        IPoint4<R> translatedSink = GeometricOperations.translate(
                lineToTranslate.getSink(), translation, returnType);


        return new Line4<R>(translatedSource, translatedSink, returnType);
    }

    /**
     * Reverts the translation of the line by the translation.
     * 
     * @param lineToTranslate
     *            the line to translate.
     * @param translation
     *            the translation.
     * @return the translated line.
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> revertTranslate(
            final ILine4<T> lineToTranslate, final ITranslation<S> translation) {

        IPoint4<? extends Number> translatedSource = GeometricOperations
                .revertTranslate(lineToTranslate.getSource(), translation);

        IPoint4<? extends Number> translatedSink = GeometricOperations
                .revertTranslate(lineToTranslate.getSink(), translation);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(translatedSource.getType());
        classes.add(translatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(translatedSource, translatedSink,
                highestPrecision);
    }

    /**
     * Reverts the translation of the line by the translation.
     * 
     * @param lineToTranslate
     *            the line to translate.
     * @param translation
     *            the translation.
     * @param returnType
     *            the desired return type.
     * @return the translated line.
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> revertTranslate(
            final ILine4<T> lineToTranslate, final ITranslation<S> translation,
            Class<? extends R> returnType) {

        IPoint4<R> translatedSource = GeometricOperations.revertTranslate(
                lineToTranslate.getSource(), translation, returnType);

        IPoint4<R> translatedSink = GeometricOperations.revertTranslate(
                lineToTranslate.getSink(), translation, returnType);


        return new Line4<R>(translatedSource, translatedSink, returnType);
    }

    /**
     * Scales the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled line.
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> scale(
            final ILine4<T> lineToScale, final IScale<S> scalingFactor) {

        IPoint4<? extends Number> scaledSource = GeometricOperations.scale(
                lineToScale.getSource(), scalingFactor);

        IPoint4<? extends Number> scaledSink = GeometricOperations.scale(
                lineToScale.getSink(), scalingFactor);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(scaledSource.getType());
        classes.add(scaledSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(scaledSource, scaledSink, highestPrecision);
    }

    /**
     * Scales the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled line.
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> scale(
            final ILine4<T> lineToScale, final IScale<S> scalingFactor,
            final Class<? extends R> returnType) {

        IPoint4<R> scaledSource = GeometricOperations.scale(
                lineToScale.getSource(), scalingFactor, returnType);

        IPoint4<R> scaledSink = GeometricOperations.scale(
                lineToScale.getSink(), scalingFactor, returnType);


        return new Line4<R>(scaledSource, scaledSink, returnType);
    }

    /**
     * Reverts the scaling of the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled line.
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> revertScale(
            final ILine4<T> lineToScale, final IScale<S> scalingFactor) {

        IPoint4<? extends Number> scaledSource = GeometricOperations
                .revertScale(lineToScale.getSource(), scalingFactor);

        IPoint4<? extends Number> scaledSink = GeometricOperations.revertScale(
                lineToScale.getSink(), scalingFactor);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(scaledSource.getType());
        classes.add(scaledSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(scaledSource, scaledSink, highestPrecision);
    }

    /**
     * Reverts the scaling of the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled line.
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> revertScale(
            final ILine4<T> lineToScale, final IScale<S> scalingFactor,
            final Class<? extends R> returnType) {

        IPoint4<R> scaledSource = GeometricOperations.revertScale(
                lineToScale.getSource(), scalingFactor, returnType);

        IPoint4<R> scaledSink = GeometricOperations.revertScale(
                lineToScale.getSink(), scalingFactor, returnType);


        return new Line4<R>(scaledSource, scaledSink, returnType);
    }

    /**
     * Uniformly scales the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled line.
     */
    public static <T extends Number> ILine4<? extends Number> linearScale(
            final ILine4<T> lineToScale, final Number scalingFactor) {

        IPoint4<? extends Number> scaledSource = GeometricOperations
                .linearScale(lineToScale.getSource(), scalingFactor);

        IPoint4<? extends Number> scaledSink = GeometricOperations.linearScale(
                lineToScale.getSink(), scalingFactor);


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(scaledSource.getType());
        classes.add(scaledSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(scaledSource, scaledSink, highestPrecision);
    }

    /**
     * Uniformly scales the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled line.
     */
    public static <T extends Number, R extends Number> ILine4<R> linearScale(
            final ILine4<T> lineToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> scaledSource = GeometricOperations
                .linearScale(lineToScale.getSource(), scalingFactor);

        IPoint4<? extends Number> scaledSink = GeometricOperations.linearScale(
                lineToScale.getSink(), scalingFactor);


        return new Line4<R>(scaledSource, scaledSink, returnType);
    }

    /**
     * Reverts the uniform scaling of the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the scaled line.
     */
    public static <T extends Number> ILine4<? extends Number> revertLinearScale(
            final ILine4<T> lineToScale, final Number scalingFactor) {

        IPoint4<? extends Number> scaledSource = GeometricOperations
                .revertLinearScale(lineToScale.getSource(), scalingFactor);

        IPoint4<? extends Number> scaledSink = GeometricOperations
                .revertLinearScale(lineToScale.getSink(), scalingFactor);



        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(scaledSource.getType());
        classes.add(scaledSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(scaledSource, scaledSink, highestPrecision);
    }

    /**
     * Reverts the uniform scaling of the line by the scaling factor.
     * 
     * @param lineToScale
     *            the line to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled line.
     */
    public static <T extends Number, R extends Number> ILine4<R> revertLinearScale(
            final ILine4<T> lineToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> scaledSource = GeometricOperations
                .revertLinearScale(lineToScale.getSource(), scalingFactor);

        IPoint4<? extends Number> scaledSink = GeometricOperations
                .revertLinearScale(lineToScale.getSink(), scalingFactor);

        return new Line4<R>(scaledSource, scaledSink, returnType);
    }

    /**
     * Shears the line by the provided shear factor.
     * 
     * @param lineToShear
     *            the line to shear.
     * @param shear
     *            the shear.
     * @return the sheared line.
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> shear(
            final ILine4<T> lineToShear, final IShear<S> shear) {

        IPoint4<? extends Number> shearedSource = GeometricOperations.shear(
                lineToShear.getSource(), shear);

        IPoint4<? extends Number> shearedSink = GeometricOperations.shear(
                lineToShear.getSink(), shear);


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(shearedSource.getType());
        classes.add(shearedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(shearedSource, shearedSink, highestPrecision);
    }

    /**
     * Shears the line by the provided shear factor.
     * 
     * @param lineToShear
     *            the line to shear.
     * @param shear
     *            the shear.
     * @param returnType
     *            the desired return type.
     * @return the sheared line.
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> shear(
            final ILine4<T> lineToShear, final IShear<S> shear,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> shearedSource = GeometricOperations.shear(
                lineToShear.getSource(), shear);

        IPoint4<? extends Number> shearedSink = GeometricOperations.shear(
                lineToShear.getSink(), shear);

        return new Line4<R>(shearedSource, shearedSink, returnType);
    }

    /**
     * Reverts the shear of the line by the provided shear factor.
     * 
     * @param lineToShear
     *            the line to shear.
     * @param shear
     *            the shear.
     * @return the sheared line.
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> revertShear(
            final ILine4<T> lineToShear, final IShear<S> shear) {

        IPoint4<? extends Number> shearedSource = GeometricOperations
                .revertShear(lineToShear.getSource(), shear);

        IPoint4<? extends Number> shearedSink = GeometricOperations
                .revertShear(lineToShear.getSink(), shear);


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(shearedSource.getType());
        classes.add(shearedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(shearedSource, shearedSink, highestPrecision);
    }

    /**
     * Reverts the shear of the line by the provided shear factor.
     * 
     * @param lineToShear
     *            the line to shear.
     * @param shear
     *            the shear.
     * @param returnType
     *            the desired return type.
     * @return the sheared line.
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> revertShear(
            final ILine4<T> lineToShear, final IShear<S> shear,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> shearedSource = GeometricOperations
                .revertShear(lineToShear.getSource(), shear);

        IPoint4<? extends Number> shearedSink = GeometricOperations
                .revertShear(lineToShear.getSink(), shear);

        return new Line4<R>(shearedSource, shearedSink, returnType);
    }

    /**
     * Rotates the line around by the rotation angle around the rotation axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> rotate(
            final ILine4<T> lineToRotate, final Rotation<S> rotation) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotate(
                lineToRotate.getSource(), rotation);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotate(
                lineToRotate.getSink(), rotation);


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Rotates the line around by the rotation angle around the rotation axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotation
     *            the rotation.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<R> rotate(
            final ILine4<T> lineToRotate, final Rotation<S> rotation,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotate(
                lineToRotate.getSource(), rotation);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotate(
                lineToRotate.getSink(), rotation);


        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * rotation axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotation
     *            the rotation.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, S extends Number> ILine4<? extends Number> revertRotate(
            final ILine4<T> lineToRotate, final IRotation<S> rotation) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotate(lineToRotate.getSource(), rotation);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotate(lineToRotate.getSink(), rotation);


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * rotation axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotation
     *            the rotation.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> ILine4<? extends Number> revertRotate(
            final ILine4<T> lineToRotate, final IRotation<S> rotation,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotate(lineToRotate.getSource(), rotation);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotate(lineToRotate.getSink(), rotation);

        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Rotates the line around by the rotation angle around the x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static <T extends Number> ILine4<? extends Number> rotateX(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotateX(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotateX(
                lineToRotate.getSink(), rotationAngle);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Rotates the line around by the rotation angle around the x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, R extends Number> ILine4<R> rotateX(
            final ILine4<T> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotateX(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotateX(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static <T extends Number> ILine4<? extends Number> revertRotateX(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotateX(lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotateX(lineToRotate.getSink(), rotationAngle);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, R extends Number> ILine4<R> revertRotateX(
            final ILine4<T> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotateX(lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotateX(lineToRotate.getSink(), rotationAngle);

        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Rotates the line around by the rotation angle around the y-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static <T extends Number> ILine4<? extends Number> rotateY(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotateY(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotateY(
                lineToRotate.getSink(), rotationAngle);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Rotates the line around by the rotation angle around the y-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, R extends Number> ILine4<R> rotateY(
            final ILine4<T> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotateY(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotateY(
                lineToRotate.getSink(), rotationAngle);

        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static <T extends Number> ILine4<? extends Number> revertRotateY(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotateY(lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotateY(lineToRotate.getSink(), rotationAngle);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, R extends Number> ILine4<R> revertRotateY(
            final ILine4<T> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotateY(lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotateY(lineToRotate.getSink(), rotationAngle);

        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Rotates the line around by the rotation angle around the z-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static <T extends Number> ILine4<? extends Number> rotateZ(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotateZ(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotateZ(
                lineToRotate.getSink(), rotationAngle);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Rotates the line around by the rotation angle around the z-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, R extends Number> ILine4<R> rotateZ(
            final ILine4<T> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations.rotateZ(
                lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations.rotateZ(
                lineToRotate.getSink(), rotationAngle);


        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated line.
     * 
     */
    public static <T extends Number> ILine4<? extends Number> revertRotateZ(
            final ILine4<T> lineToRotate, final Number rotationAngle) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotateZ(lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotateZ(lineToRotate.getSink(), rotationAngle);

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(rotatedSource.getType());
        classes.add(rotatedSink.getType());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Line4<Number>(rotatedSource, rotatedSink, highestPrecision);
    }

    /**
     * Reverts the rotation of the line around by the rotation angle around the
     * x-axis.
     * 
     * @param lineToRotate
     *            the line to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated line.
     * 
     */
    public static <T extends Number, R extends Number> ILine4<R> revertRotateZ(
            final ILine4<T> lineToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {

        IPoint4<? extends Number> rotatedSource = GeometricOperations
                .revertRotateZ(lineToRotate.getSource(), rotationAngle);

        IPoint4<? extends Number> rotatedSink = GeometricOperations
                .revertRotateZ(lineToRotate.getSink(), rotationAngle);


        return new Line4<R>(rotatedSource, rotatedSink, returnType);
    }

    /**
     * Creates the translation matrix from the provided translation.
     * 
     * @param translation
     *            the translation.
     * @return the translation matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> translationMatrix(
            final ITranslation<T> translation) {

        return GeometricOperations.translationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation());
    }

    /**
     * Creates the translation matrix from the provided translation.
     * 
     * @param translation
     *            the translation.
     * @param returnType
     *            the desired return type.
     * @return the translation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> translationMatrix(
            final ITranslation<T> translation, Class<? extends R> returnType) {

        return GeometricOperations.translationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation(), returnType);
    }

    /**
     * Creates the translation matrix from the provided components.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @return the translation matrix.
     */
    public static IMatrix4<? extends Number> translationMatrix(final Number tx,
            final Number ty, final Number tz) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                3);
        classes.add(tx.getClass());
        classes.add(ty.getClass());
        classes.add(tz.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Matrix4<Number>(Vector4.I_UNIT_VECTOR(highestPrecision),
                Vector4.J_UNIT_VECTOR(highestPrecision),
                Vector4.K_UNIT_VECTOR(highestPrecision), new Point4<Number>(tx,
                        ty, tz, highestPrecision), highestPrecision);
    }

    /**
     * Creates the translation matrix from the provided components.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @param returnType
     *            the desired return type.
     * @return the translation matrix.
     */
    public static <R extends Number> IMatrix4<R> translationMatrix(
            final Number tx, final Number ty, final Number tz,
            Class<? extends R> returnType) {


        return new Matrix4<R>(Vector4.I_UNIT_VECTOR(returnType),
                Vector4.J_UNIT_VECTOR(returnType),
                Vector4.K_UNIT_VECTOR(returnType), new Point4<R>(tx, ty, tz,
                        returnType), returnType);
    }

    /**
     * Creates the inverse translation matrix from the provided translation.
     * 
     * @param translation
     *            the translation.
     * @return the inverse translation matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> inverseTranslationMatrix(
            final ITranslation<T> translation) {

        return GeometricOperations.inverseTranslationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation());
    }

    /**
     * Creates the inverse translation matrix from the provided translation.
     * 
     * @param translation
     *            the translation.
     * @param returnType
     *            the desired return type.
     * @return the inverse translation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> inverseTranslationMatrix(
            final ITranslation<T> translation, Class<? extends R> returnType) {

        return GeometricOperations.inverseTranslationMatrix(
                translation.getXTranslation(), translation.getYTranslation(),
                translation.getZTranslation(), returnType);
    }

    /**
     * Creates the inverse translation matrix from the provided components.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @return the translation matrix.
     */
    public static IMatrix4<? extends Number> inverseTranslationMatrix(
            final Number tx, final Number ty, final Number tz) {

        Number minusTx = ArithmeticOperations.subtract(0, tx);
        Number minusTy = ArithmeticOperations.subtract(0, ty);
        Number minusTz = ArithmeticOperations.subtract(0, tz);

        return GeometricOperations.translationMatrix(minusTx, minusTy, minusTz);
    }

    /**
     * Creates the inverse translation matrix from the provided components.
     * 
     * @param tx
     *            the x translation component.
     * @param ty
     *            the y translation component.
     * @param tz
     *            the z translation component.
     * @param returnType
     *            the desired return type.
     * @return the translation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> inverseTranslationMatrix(
            final Number tx, final Number ty, final Number tz,
            Class<? extends R> returnType) {

        Number minusTx = ArithmeticOperations.subtract(0, tx);
        Number minusTy = ArithmeticOperations.subtract(0, ty);
        Number minusTz = ArithmeticOperations.subtract(0, tz);

        return GeometricOperations.translationMatrix(minusTx, minusTy, minusTz,
                returnType);
    }

    /**
     * Creates the scaling matrix from the provided scale.
     * 
     * @param scaling
     *            the scale.
     * @return the scaling matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> scalingMatrix(
            final IScale<T> scaling) {

        return GeometricOperations.scalingMatrix(scaling.getScaleFactorX(),
                scaling.getScaleFactorY(), scaling.getScaleFactorZ());
    }

    /**
     * Creates the scaling matrix from the provided scale.
     * 
     * @param scaling
     *            the scale.
     * @param returnType
     *            the desired return type.
     * @return the scaling matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> scalingMatrix(
            final Scale<T> scaling, final Class<? extends R> returnType) {

        return GeometricOperations.scalingMatrix(scaling.getScaleFactorX(),
                scaling.getScaleFactorY(), scaling.getScaleFactorZ(),
                returnType);
    }

    /**
     * Creates the scaling matrix from the provided components.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @return the scaling matrix.
     */
    public static IMatrix4<? extends Number> scalingMatrix(final Number sx,
            final Number sy, final Number sz) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                3);
        classes.add(sx.getClass());
        classes.add(sy.getClass());
        classes.add(sz.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Matrix4<Number>(VectorAlgebraicOperations.multiply(
                Vector4.I_UNIT_VECTOR(highestPrecision), sx),
                VectorAlgebraicOperations.multiply(
                        Vector4.J_UNIT_VECTOR(highestPrecision), sy),
                VectorAlgebraicOperations.multiply(
                        Vector4.K_UNIT_VECTOR(highestPrecision), sz),
                Point4.ORIGIN(highestPrecision), highestPrecision);
    }

    /**
     * Creates the scaling matrix from the provided components.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @param returnType
     *            the desired return type.
     * @return the scaling matrix.
     */
    public static <R extends Number> IMatrix4<R> scalingMatrix(final Number sx,
            final Number sy, final Number sz,
            final Class<? extends R> returnType) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                3);
        classes.add(sx.getClass());
        classes.add(sy.getClass());
        classes.add(sz.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Matrix4<R>(VectorAlgebraicOperations.multiply(
                Vector4.I_UNIT_VECTOR(highestPrecision), sx),
                VectorAlgebraicOperations.multiply(
                        Vector4.J_UNIT_VECTOR(highestPrecision), sy),
                VectorAlgebraicOperations.multiply(
                        Vector4.K_UNIT_VECTOR(highestPrecision), sz),
                Point4.ORIGIN(highestPrecision), returnType);
    }

    /**
     * Creates the inverse scaling matrix from the provided scale.
     * 
     * @param scaling
     *            the scale
     * @return the inverse scaling matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> inverseScalingMatrix(
            final IScale<T> scaling) {

        return GeometricOperations.inverseScalingMatrix(
                scaling.getScaleFactorX(), scaling.getScaleFactorY(),
                scaling.getScaleFactorZ());
    }

    /**
     * Creates the inverse scaling matrix from the provided scale.
     * 
     * @param scaling
     *            the scale
     * @param returnType
     *            the desired return type.
     * @return the inverse scaling matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> inverseScalingMatrix(
            final IScale<T> scaling, final Class<? extends R> returnType) {

        return GeometricOperations.inverseScalingMatrix(
                scaling.getScaleFactorX(), scaling.getScaleFactorY(),
                scaling.getScaleFactorZ(), returnType);
    }

    /**
     * Creates the inverse scaling matrix from the provided components.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @return the scaling matrix.
     */
    public static IMatrix4<? extends Number> inverseScalingMatrix(
            final Number sx, final Number sy, final Number sz) {


        return GeometricOperations.scalingMatrix(
                ArithmeticOperations.divide(1, sx),
                ArithmeticOperations.divide(1, sy),
                ArithmeticOperations.divide(1, sz));
    }

    /**
     * Creates the inverse scaling matrix from the provided components.
     * 
     * @param sx
     *            the x scaling component.
     * @param sy
     *            the y scaling component.
     * @param sz
     *            the z scaling component.
     * @param returnType
     *            the desired return type.
     * @return the scaling matrix.
     */
    public static <R extends Number> IMatrix4<R> inverseScalingMatrix(
            final Number sx, final Number sy, final Number sz,
            final Class<? extends R> returnType) {

        return GeometricOperations.scalingMatrix(
                ArithmeticOperations.divide(1, sx),
                ArithmeticOperations.divide(1, sy),
                ArithmeticOperations.divide(1, sz), returnType);
    }


    /**
     * Creates the linear scaling matrix from the provided components.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @return the scaling transformation.
     */
    public static IMatrix4<? extends Number> linearScalingMatrix(
            final Number scalingFactor) {


        return GeometricOperations.scalingMatrix(scalingFactor, scalingFactor,
                scalingFactor);
    }

    /**
     * Creates the linear scaling matrix from the provided components.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @param returnType
     *            the desired return type.
     * @return the scaling transformation.
     */
    public static <R extends Number> IMatrix4<R> linearScalingMatrix(
            final Number scalingFactor, final Class<? extends R> returnType) {


        return GeometricOperations.scalingMatrix(scalingFactor, scalingFactor,
                scalingFactor, returnType);
    }


    /**
     * Creates the inverse linear scaling matrix from the provided components.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @return the scaling transformation.
     */
    public static IMatrix4<? extends Number> inverseLinearScalingMatrix(
            final Number scalingFactor) {


        return GeometricOperations.inverseScalingMatrix(scalingFactor,
                scalingFactor, scalingFactor);
    }

    /**
     * Creates the inverse linear scaling matrix from the provided components.
     * 
     * @param scalingFactor
     *            the scaling factor .
     * @param returnType
     *            the desired return type.
     * @return the scaling transformation.
     */
    public static <R extends Number> IMatrix4<R> inverseLinearScalingMatrix(
            final Number scalingFactor, final Class<? extends R> returnType) {


        return GeometricOperations.inverseScalingMatrix(scalingFactor,
                scalingFactor, scalingFactor, returnType);
    }


    /**
     * Creates the shear matrix from the provided shear.
     * 
     * @param shear
     *            the shear
     * @return the shear matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> shearMatrix(
            final IShear<T> shear) {

        return GeometricOperations.shearMatrix(shear.getShearCoefficient(),
                shear.getShearBy());
    }

    /**
     * Creates the shear matrix from the provided shear.
     * 
     * @param shear
     *            the shear
     * @param returnType
     *            the desired return type.
     * @return the shear matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> shearMatrix(
            final IShear<T> shear, final Class<? extends R> returnType) {

        return GeometricOperations.shearMatrix(shear.getShearCoefficient(),
                shear.getShearBy(), returnType);
    }


    /**
     * Creates the shear matrix for shearing by the shearCoefficient and the
     * selected component.
     * 
     * @param shearCoefficient
     *            the x translation component.
     * @param shearBy
     *            the component to shear by.
     * @return the shear matrix.
     */
    public static IMatrix4<? extends Number> shearMatrix(
            final Number shearCoefficient, final ShearBy shearBy) {

        return GeometricOperations.shearMatrix(shearCoefficient, shearBy,
                shearCoefficient.getClass());
    }

    /**
     * Creates the shear matrix for shearing by the shearCoefficient and the
     * selected component.
     * 
     * @param shearCoefficient
     *            the x translation component.
     * @param shearBy
     *            the component to shear by.
     * @param returnType
     *            the desired return type.
     * @return the shear matrix.
     */
    public static <R extends Number> IMatrix4<R> shearMatrix(
            final Number shearCoefficient, final ShearBy shearBy,
            Class<? extends R> returnType) {

        IVector4<? extends Number> i = null;
        IVector4<? extends Number> j = null;
        IVector4<? extends Number> k = null;

        switch (shearBy) {
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

        return new Matrix4<R>(i, j, k, Point4.ORIGIN(returnType), returnType);
    }

    /**
     * Creates the inverse shear matrix for the provided shear.
     * 
     * @param shear
     *            the shear.
     * @return the inverse shear matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> inverseShearMatrix(
            final IShear<T> shear) {

        return GeometricOperations.inverseShearMatrix(
                shear.getShearCoefficient(), shear.getShearBy());
    }

    /**
     * Creates the inverse shear matrix for the provided shear.
     * 
     * @param shear
     *            the shear.
     * @param returnType
     *            the desired return type.
     * @return the inverse shear matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> inverseShearMatrix(
            final IShear<T> shear, final Class<? extends R> returnType) {

        return GeometricOperations.inverseShearMatrix(
                shear.getShearCoefficient(), shear.getShearBy(), returnType);
    }

    /**
     * Creates the inverse shear matrix for shearing by the shearCoefficient and
     * the selected component.
     * 
     * @param shearCoefficient
     *            the x translation component.
     * @param shearBy
     *            the component to shear by.
     * @return the inverse shear matrix.
     */
    public static IMatrix4<? extends Number> inverseShearMatrix(
            final Number shearCoefficient, final ShearBy shearBy) {

        return GeometricOperations.inverseShearMatrix(shearCoefficient,
                shearBy, shearCoefficient.getClass());
    }

    /**
     * Creates the inverse shear matrix for shearing by the shearCoefficient and
     * the selected component.
     * 
     * @param shearCoefficient
     *            the x translation component.
     * @param shearBy
     *            the component to shear by.
     * @param returnType
     *            the desired return type.
     * @return the inverse shear matrix.
     */
    public static <R extends Number> IMatrix4<R> inverseShearMatrix(
            final Number shearCoefficient, final ShearBy shearBy,
            final Class<? extends R> returnType) {

        return GeometricOperations.shearMatrix(
                ArithmeticOperations.subtract(0, shearCoefficient), shearBy,
                returnType);
    }

    /**
     * Creates the rotation matrix for a rotation.
     * 
     * @param rotation
     *            the rotation.
     * @return the rotation matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> rotationMatrix(
            final IRotation<T> rotation) {

        return GeometricOperations.rotationMatrix(rotation.getAxis(),
                rotation.getAngle());
    }

    /**
     * Creates the rotation matrix for a rotation.
     * 
     * @param rotation
     *            the rotation.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> rotationMatrix(
            final IRotation<T> rotation, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(rotation.getAxis(),
                rotation.getAngle(), returnType);
    }

    /**
     * Creates the rotation matrix for a rotation around the rotation axis by
     * the provided angle.
     * 
     * @param rotationAxis
     *            the axis of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> rotationMatrix(
            final ITuple4<T> rotationAxis, final Number rotationAngle) {

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

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                3);
        classes.add(highestPrecisionI);
        classes.add(highestPrecisionJ);
        classes.add(highestPrecisionK);
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Matrix4<Number>(i, j, k, Point4.ORIGIN(highestPrecision),
                highestPrecision);
    }

    /**
     * Creates the rotation matrix for a rotation around the rotation axis by
     * the provided angle.
     * 
     * @param rotationAxis
     *            the axis of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> rotationMatrix(
            final ITuple4<T> rotationAxis, final Number rotationAngle,
            final Class<? extends R> returnType) {

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

        return new Matrix4<R>(i, j, k, Point4.ORIGIN(returnType), returnType);
    }

    /**
     * Creates the inverse rotation matrix for a rotation.
     * 
     * @param rotation
     *            the rotation.
     * @return the rotation matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> inverseRotationMatrix(
            final IRotation<T> rotation) {

        return GeometricOperations.inverseRotationMatrix(rotation.getAxis(),
                rotation.getAngle());
    }

    /**
     * Creates the inverse rotation matrix for a rotation.
     * 
     * @param rotation
     *            the rotation.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> inverseRotationMatrix(
            final IRotation<T> rotation, final Class<R> returnType) {

        return GeometricOperations.inverseRotationMatrix(rotation.getAxis(),
                rotation.getAngle(), returnType);
    }

    /**
     * Creates the inverse of the rotation matrix for a rotation around the
     * rotation axis by the provided angle.
     * 
     * @param rotationAxis
     *            the axis of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static <T extends Number> IMatrix4<? extends Number> inverseRotationMatrix(
            final IVector4<T> rotationAxis, final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(rotationAxis, rotationAngle)
                .transpose();
    }

    /**
     * Creates the inverse of the rotation matrix for a rotation around the
     * rotation axis by the provided angle.
     * 
     * @param rotationAxis
     *            the axis of rotation.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <T extends Number, R extends Number> IMatrix4<R> inverseRotationMatrix(
            final IVector4<T> rotationAxis, final Number rotationAngle,
            final Class<R> returnType) {

        return GeometricOperations.rotationMatrix(rotationAxis, rotationAngle,
                returnType).transpose();
    }


    /**
     * Creates the rotation matrix around the x-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static IMatrix4<? extends Number> xRotationMatrix(
            final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(
                Vector4.I_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle);
    }

    /**
     * Creates the rotation matrix around the x-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <R extends Number> IMatrix4<R> xRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.I_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType);
    }


    /**
     * Creates the inverse of the rotation matrix around the x-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static IMatrix4<? extends Number> inverseXRotationMatrix(
            final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(
                Vector4.I_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle)
                .transpose();
    }

    /**
     * Creates the inverse of the rotation matrix around the x-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <R extends Number> IMatrix4<R> inverseXRotationMatrix(
            final Number rotationAngle, final Class<R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.I_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType).transpose();
    }


    /**
     * Creates the rotation matrix around the y-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static IMatrix4<? extends Number> yRotationMatrix(
            final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(
                Vector4.J_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle);
    }

    /**
     * Creates the rotation matrix around the y-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <R extends Number> IMatrix4<R> yRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.J_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType);
    }


    /**
     * Creates the inverse of the rotation matrix around the y-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static IMatrix4<? extends Number> inverseYRotationMatrix(
            final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(
                Vector4.J_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle)
                .transpose();
    }

    /**
     * Creates the inverse of the rotation matrix around the y-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <R extends Number> IMatrix4<R> inverseYRotationMatrix(
            final Number rotationAngle, final Class<R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.J_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType).transpose();
    }


    /**
     * Creates the rotation matrix around the z-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static IMatrix4<? extends Number> zRotationMatrix(
            final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(
                Vector4.K_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle);
    }

    /**
     * Creates the rotation matrix around the z-axis by the provided rotation
     * angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <R extends Number> IMatrix4<R> zRotationMatrix(
            final Number rotationAngle, final Class<? extends R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.K_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType);
    }


    /**
     * Creates the inverse of the rotation matrix around the z-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotation matrix.
     */
    public static IMatrix4<? extends Number> inverseZRotationMatrix(
            final Number rotationAngle) {

        return GeometricOperations.rotationMatrix(
                Vector4.K_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle)
                .transpose();
    }

    /**
     * Creates the inverse of the rotation matrix around the z-axis by the
     * provided rotation angle.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotation matrix.
     */
    public static <R extends Number> IMatrix4<R> inverseZRotationMatrix(
            final Number rotationAngle, final Class<R> returnType) {

        return GeometricOperations.rotationMatrix(
                Vector4.K_UNIT_VECTOR(rotationAngle.getClass()), rotationAngle,
                returnType).transpose();
    }


    /**
     * Translates the tuple by the x, y, and z translation.
     * 
     * @param tupleToTranslate
     *            the tuple to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @return the translated tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> translate(
            final ITuple4<T> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .translationMatrix(xTranslation, yTranslation, zTranslation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate);
    }

    /**
     * Translates the tuple by the x, y, and z translation.
     * 
     * @param tupleToTranslate
     *            the tuple to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @param returnType
     *            the desired return type.
     * @return the translated tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> translate(
            final ITuple4<T> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation,
            final Class<? extends R> returnType) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .translationMatrix(xTranslation, yTranslation, zTranslation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate, returnType);
    }


    /**
     * Reverts the translate of the tuple by the x, y, and z translation.
     * 
     * @param tupleToTranslate
     *            the tuple to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @return the original tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> revertTranslate(
            final ITuple4<T> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(xTranslation, yTranslation,
                        zTranslation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate);
    }

    /**
     * Reverts the translate of the tuple by the x, y, and z translation.
     * 
     * @param tupleToTranslate
     *            the tuple to translate.
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertTranslate(
            final ITuple4<T> tupleToTranslate, final Number xTranslation,
            final Number yTranslation, final Number zTranslation,
            Class<? extends R> returnType) {

        IMatrix4<? extends Number> translationMatrix = GeometricOperations
                .inverseTranslationMatrix(xTranslation, yTranslation,
                        zTranslation);

        return VectorAlgebraicOperations.multiply(translationMatrix,
                tupleToTranslate, returnType);
    }


    /**
     * Scales the tuple by the x, y, and z scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @return the scaled tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> scale(
            final ITuple4<T> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .scalingMatrix(xScalingFactor, yScalingFactor, zScalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale);
    }

    /**
     * Scales the tuple by the x, y, and z scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the scaled tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> scale(
            final ITuple4<T> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .scalingMatrix(xScalingFactor, yScalingFactor, zScalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale,
                returnType);
    }


    /**
     * Revert scaling of the tuple by the x, y, and z scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @return the original tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> revertScale(
            final ITuple4<T> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .inverseScalingMatrix(xScalingFactor, yScalingFactor,
                        zScalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale);
    }

    /**
     * Revert scaling of the tuple by the x, y, and z scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param xScalingFactor
     *            the x scaling factor.
     * @param yScalingFactor
     *            the y scaling factor.
     * @param zScalingFactor
     *            the z scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertScale(
            final ITuple4<T> tupleToScale, final Number xScalingFactor,
            final Number yScalingFactor, final Number zScalingFactor,
            final Class<? extends R> returnType) {

        IMatrix4<? extends Number> scaleMatrix = GeometricOperations
                .inverseScalingMatrix(xScalingFactor, yScalingFactor,
                        zScalingFactor);

        return VectorAlgebraicOperations.multiply(scaleMatrix, tupleToScale,
                returnType);
    }


    /**
     * Uniformly scales the tuple by the scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the uniformly scaled tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> linearScale(
            final ITuple4<T> tupleToScale, final Number scalingFactor) {

        return GeometricOperations.scale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor);
    }

    /**
     * Uniformly scales the tuple by the scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the uniformly scaled tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> linearScale(
            final ITuple4<T> tupleToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        return GeometricOperations.scale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor, returnType);
    }


    /**
     * Reverts the uniform scale of the tuple by the scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @return the original tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> revertLinearScale(
            final ITuple4<T> tupleToScale, final Number scalingFactor) {

        return GeometricOperations.revertScale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor);
    }

    /**
     * Reverts the uniform scale of the tuple by the scaling factor.
     * 
     * @param tupleToScale
     *            the tuple to scale.
     * @param scalingFactor
     *            the scaling factor.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertLinearScale(
            final ITuple4<T> tupleToScale, final Number scalingFactor,
            final Class<? extends R> returnType) {

        return GeometricOperations.revertScale(tupleToScale, scalingFactor,
                scalingFactor, scalingFactor, returnType);
    }


    /**
     * Shears the tuple by the provided shear factor and the provided component.
     * 
     * @param tupleToShear
     *            the tuple to shear.
     * @param shearFactor
     *            the shear factor.
     * @param shearBy
     *            identifier of the component to shear by.
     * @return the sheared tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> shear(
            final ITuple4<T> tupleToShear, final Number shearFactor,
            final ShearBy shearBy) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .shearMatrix(shearFactor, shearBy);


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear);
    }

    /**
     * Shears the tuple by the provided shear factor and the provided component.
     * 
     * @param tupleToShear
     *            the tuple to shear.
     * @param shearFactor
     *            the shear factor.
     * @param shearBy
     *            identifier of the component to shear by.
     * @param returnType
     *            the desired return type.
     * @return the sheared tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> shear(
            final ITuple4<T> tupleToShear, final Number shearFactor,
            final ShearBy shearBy, final Class<? extends R> returnType) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .shearMatrix(shearFactor, shearBy);


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear,
                returnType);
    }


    /**
     * Reverts the shear of the tuple by the provided shear factor and the
     * provided component.
     * 
     * @param tupleToShear
     *            the tuple to shear.
     * @param shearFactor
     *            the shear factor.
     * @param shearBy
     *            identifier of the component to shear by.
     * @return the original tuple.
     */
    public static <T extends Number> ITuple4<? extends Number> revertShear(
            final ITuple4<T> tupleToShear, final Number shearFactor,
            final ShearBy shearBy) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .inverseShearMatrix(shearFactor, shearBy);


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear);
    }

    /**
     * Reverts the shear of the tuple by the provided shear factor and the
     * provided component.
     * 
     * @param tupleToShear
     *            the tuple to shear.
     * @param shearFactor
     *            the shear factor.
     * @param shearBy
     *            identifier of the component to shear by.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertShear(
            final ITuple4<T> tupleToShear, final Number shearFactor,
            final ShearBy shearBy, final Class<? extends R> returnType) {

        IMatrix4<? extends Number> shearMatrix = GeometricOperations
                .inverseShearMatrix(shearFactor, shearBy);


        return VectorAlgebraicOperations.multiply(shearMatrix, tupleToShear,
                returnType);
    }


    /**
     * Rotates the tuple around by the rotation angle around the rotation axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number, S extends Number> ITuple4<? extends Number> rotate(
            final ITuple4<T> tupleToRotate, final IVector4<S> rotationAxis,
            final Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .rotationMatrix(rotationAxis, rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, tupleToRotate);
    }

    /**
     * Rotates the tuple around by the rotation angle around the rotation axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> ITuple4<R> rotate(
            final ITuple4<T> tupleToRotate, final IVector4<S> rotationAxis,
            final Number rotationAngle, final Class<? extends R> returnType) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .rotationMatrix(rotationAxis, rotationAngle);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                tupleToRotate, returnType);
    }


    /**
     * Reverses the rotation of the tuple around by the rotation angle around
     * the rotation axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static <T extends Number, S extends Number> ITuple4<? extends Number> revertRotate(
            final ITuple4<T> tupleToRotate, final IVector4<S> rotationAxis,
            final Number rotationAngle) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseRotationMatrix(rotationAxis, rotationAngle);

        return VectorAlgebraicOperations
                .multiply(rotationMatrix, tupleToRotate);
    }

    /**
     * Reverses the rotation of the tuple around by the rotation angle around
     * the rotation axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     * 
     */
    public static <T extends Number, S extends Number, R extends Number> ITuple4<R> revertRotate(
            final ITuple4<T> tupleToRotate, final IVector4<S> rotationAxis,
            final Number rotationAngle, final Class<? extends R> returnType) {

        IMatrix4<? extends Number> rotationMatrix = GeometricOperations
                .inverseRotationMatrix(rotationAxis, rotationAngle);

        return VectorAlgebraicOperations.multiply(rotationMatrix,
                tupleToRotate, returnType);
    }


    /**
     * Rotates the tuple by the rotation angle around the x-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number> ITuple4<? extends Number> rotateX(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {



        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(highestPrecision), rotationAngle);
    }

    /**
     * Rotates the tuple by the rotation angle around the x-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number, R extends Number> ITuple4<R> rotateX(
            final ITuple4<T> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(highestPrecision), rotationAngle,
                returnType);
    }


    /**
     * Reverts rotation of the tuple by the rotation angle around the x-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static <T extends Number> ITuple4<? extends Number> revertRotateX(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(highestPrecision), rotationAngle);
    }

    /**
     * Reverts rotation of the tuple by the rotation angle around the x-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     * 
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertRotateX(
            final ITuple4<T> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.I_UNIT_VECTOR(highestPrecision), rotationAngle,
                returnType);
    }


    /**
     * Rotates the tuple around by the rotation angle around the y-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number> ITuple4<? extends Number> rotateY(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(highestPrecision), rotationAngle);
    }

    /**
     * Rotates the tuple around by the rotation angle around the y-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number, R extends Number> ITuple4<R> rotateY(
            final ITuple4<T> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(highestPrecision), rotationAngle,
                returnType);
    }


    /**
     * Reverts rotation of the tuple by the rotation angle around the y-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static <T extends Number> ITuple4<? extends Number> revertRotateY(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(highestPrecision), rotationAngle);
    }

    /**
     * Reverts rotation of the tuple by the rotation angle around the y-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     * 
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertRotateY(
            final ITuple4<T> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.J_UNIT_VECTOR(highestPrecision), rotationAngle,
                returnType);
    }


    /**
     * Rotates the tuple around by the rotation angle around the z-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number> ITuple4<? extends Number> rotateZ(
            final ITuple4<T> tupleToRotate, Number rotationAngle) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(highestPrecision), rotationAngle);
    }

    /**
     * Rotates the tuple around by the rotation angle around the z-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the rotated tuple.
     * 
     */
    public static <T extends Number, R extends Number> ITuple4<R> rotateZ(
            final ITuple4<T> tupleToRotate, Number rotationAngle,
            final Class<? extends R> returnType) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.rotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(highestPrecision), rotationAngle,
                returnType);
    }


    /**
     * Reverts rotation of the tuple by the rotation angle around the z-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @return the original tuple.
     * 
     */
    public static <T extends Number> ITuple4<? extends Number> revertRotateZ(
            final ITuple4<T> tupleToRotate, final Number rotationAngle) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(highestPrecision), rotationAngle);
    }

    /**
     * Reverts rotation of the tuple by the rotation angle around the z-axis.
     * 
     * @param tupleToRotate
     *            the tuple to rotate.
     * @param rotationAngle
     *            the rotation angle.
     * @param returnType
     *            the desired return type.
     * @return the original tuple.
     * 
     */
    public static <T extends Number, R extends Number> ITuple4<R> revertRotateZ(
            final ITuple4<T> tupleToRotate, final Number rotationAngle,
            final Class<? extends R> returnType) {


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(tupleToRotate.getType());
        classes.add(rotationAngle.getClass());
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return GeometricOperations.revertRotate(tupleToRotate,
                Vector4.K_UNIT_VECTOR(highestPrecision), rotationAngle,
                returnType);
    }



    /**
     * Creates the angle-axis rotation projecting the firstVector onto the
     * secondVector.
     * 
     * @param firstVector
     *            The vector to be projected.
     * @param secondVector
     *            The vector to be projected on.
     * @return The angle-axis rotation.
     */
    public static <T extends Number, S extends Number> IRotation<? extends Number> getAngleAxisRotationBetweenVectors(
            final IVector4<T> firstVector, final IVector4<S> secondVector) {

        IVector4<T> normalizedFirstVector = firstVector.normalizedVector();
        IVector4<S> normalizedSecondVector = secondVector.normalizedVector();


        Double angle = Math.acos(Vector4.dotProduct(normalizedFirstVector,
                normalizedSecondVector).doubleValue());

        IVector4<? extends Number> axis = Vector4.crossProduct(
                normalizedFirstVector, normalizedSecondVector);

        return new Rotation<Double>(axis.getX(), axis.getY(), axis.getZ(),
                angle, Double.class);
    }

    /**
     * Creates the angle-axis rotation projecting the firstVector onto the
     * secondVector.
     * 
     * @param firstVector
     *            The vector to be projected.
     * @param secondVector
     *            The vector to be projected on.
     * @param returnType
     *            the desired return type.
     * @return The angle-axis rotation.
     */
    public static <T extends Number, S extends Number, R extends Number> IRotation<R> getAngleAxisRotationBetweenVectors(
            final IVector4<T> firstVector, final IVector4<S> secondVector,
            final Class<? extends R> returnType) {

        IVector4<T> normalizedFirstVector = firstVector.normalizedVector();
        IVector4<S> normalizedSecondVector = secondVector.normalizedVector();


        Double angle = Math.acos(Vector4.dotProduct(normalizedFirstVector,
                normalizedSecondVector).doubleValue());

        IVector4<? extends Number> axis = Vector4.crossProduct(
                normalizedFirstVector, normalizedSecondVector);

        return new Rotation<R>(axis.getX(), axis.getY(), axis.getZ(), angle,
                returnType);
    }
}
