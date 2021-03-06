package org.jutility.javafx.control.wrapper;


//@formatter:off
/*
 * #%L
 * * jutility-javafx
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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.GridPane;


/**
 * The abstract {@code ComboBoxBaseWrapper} provides base functionality for
 * wrapping a {@link ComboBoxBase ComboBox} within a 3x3 {@link GridPane}.
 *
 * @param <T>
 *            The type of the value that has been selected or otherwise entered
 *            in to this ComboBox.
 * @param <S>
 *            The concrete type of the ComboBox.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 */
public abstract class ComboBoxBaseWrapper<T, S extends ComboBoxBase<T>>
        extends ControlWrapper<S> {



    /**
     * Creates a new instance of the {@code ComboBoxBaseWrapper} class.
     *
     * @param wrappedControl
     *            the control to wrap.
     */
    public ComboBoxBaseWrapper(final S wrappedControl) {

        super(wrappedControl);
    }


    /**
     * The value of this ComboBox is defined as the selected item if the input
     * is not editable, or if it is editable, the most recent user action:
     * either the value input by the user, or the last selected item.
     *
     * @return the value property.
     */
    public ObjectProperty<T> valueProperty() {

        return this.getWrappedControl().valueProperty();
    }

    /**
     * Sets the value of the property value.
     *
     * @param value
     *            the value of the property value.
     */
    public final void setValue(final T value) {

        this.getWrappedControl().setValue(value);
    }

    /**
     * Gets the value of the property value.
     *
     * @return the value of the property value.
     */
    public final T getValue() {

        return this.getWrappedControl().getValue();
    }

    /**
     * Specifies whether the ComboBox allows for user input. When editable is
     * true, the ComboBox has a text input area that a user may type in to. This
     * input is then available via the value property. Note that when the
     * editable property changes, the value property is reset, along with any
     * other relevant state.
     *
     * @return the editable property.
     */
    public BooleanProperty editableProperty() {

        return this.getWrappedControl().editableProperty();
    }


    /**
     * Sets the value of the property editable.
     *
     * @param value
     *            the value of the property editable.
     */
    public final void setEditable(final boolean value) {

        this.getWrappedControl().setEditable(value);
    }

    /**
     * Gets the value of the property editable.
     *
     * @return the value of the property editable.
     */
    public final boolean isEditable() {

        return this.getWrappedControl().isEditable();
    }

    /**
     * Represents the current state of the ComboBox popup, and whether it is
     * currently visible on screen (although it may be hidden behind other
     * windows).
     *
     * @return the showing property.
     */
    public ReadOnlyBooleanProperty showingProperty() {

        return this.getWrappedControl().showingProperty();
    }

    /**
     * Gets the value of the property showing.
     *
     * @return the value of the property showing.
     */
    public final boolean isShowing() {

        return this.getWrappedControl().isShowing();
    }

    /**
     * The ComboBox prompt text to display, or null if no prompt text is
     * displayed. Prompt text is not displayed in all circumstances, it is
     * dependent upon the subclasses of ComboBoxBase to clarify when promptText
     * will be shown. For example, in most cases prompt text will never be shown
     * when a combo box is non-editable (that is, prompt text is only shown when
     * user input is allowed via text input).
     *
     * @return the promptText property.
     */
    public final StringProperty promptTextProperty() {

        return this.getWrappedControl().promptTextProperty();
    }

    /**
     * Gets the value of the property promptText.
     *
     * @return the value of the property promptText.
     */
    public final String getPromptText() {

        return this.getWrappedControl().getPromptText();
    }

    /**
     * Sets the value of the property promptText.
     *
     * @param value
     *            the value of the property promptText.
     */
    public final void setPromptText(final String value) {

        this.getWrappedControl().setPromptText(value);
    }

    /**
     * Indicates that the ComboBox has been "armed" such that a mouse release
     * will cause the ComboBox show() method to be invoked. This is subtly
     * different from pressed. Pressed indicates that the mouse has been pressed
     * on a Node and has not yet been released. arm however also takes into
     * account whether the mouse is actually over the ComboBox and pressed.
     *
     * @return the armed property.
     */
    public BooleanProperty armedProperty() {

        return this.getWrappedControl().armedProperty();
    }

    /**
     * Gets the value of the property armed.
     *
     * @return the value of the property armed.
     */
    public final boolean isArmed() {

        return this.getWrappedControl().isArmed();
    }

    /**
     * The ComboBox action, which is invoked whenever the ComboBox value
     * property is changed. This may be due to the value property being
     * programmatically changed, when the user selects an item in a popup list
     * or dialog, or, in the case of editable ComboBoxes, it may be when the
     * user provides their own input (be that via a TextField or some other
     * input mechanism.
     *
     * @return the property onAction.
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {

        return this.getWrappedControl().onActionProperty();
    }

    /**
     * Sets the value of the property onAction.
     *
     * @param value
     *            the value of the property onAction.
     */
    public final void setOnAction(final EventHandler<ActionEvent> value) {

        this.getWrappedControl().setOnAction(value);
    }

    /**
     * Gets the value of the property onAction.
     *
     * @return the value of the property onAction.
     */
    public final EventHandler<ActionEvent> getOnAction() {

        return this.getWrappedControl().getOnAction();
    }

    /**
     * Called just prior to the ComboBoxBase popup/display being shown.
     *
     * @return the onShowing property.
     */
    public final ObjectProperty<EventHandler<Event>> onShowingProperty() {

        return this.getWrappedControl().onShowingProperty();
    }

    /**
     * Sets the value of the property onShowing.
     *
     * @param value
     *            the value of the property onShowing.
     */
    public final void setOnShowing(final EventHandler<Event> value) {

        this.getWrappedControl().setOnShowing(value);
    }

    /**
     * Gets the value of the property onShowing.
     *
     * @return the value of the property onShowing.
     */
    public final EventHandler<Event> getOnShowing() {

        return this.getWrappedControl().getOnShowing();
    }

    /**
     * Called just after the ComboBoxBase popup/display is shown.
     *
     * @return the onShown property.
     */
    public final ObjectProperty<EventHandler<Event>> onShownProperty() {

        return this.getWrappedControl().onShownProperty();
    }

    /**
     * Sets the value of the property onShown.
     *
     * @param value
     *            the value of the property onShown.
     */
    public final void setOnShown(final EventHandler<Event> value) {

        this.getWrappedControl().setOnShown(value);
    }

    /**
     * Gets the value of the property onShown.
     *
     * @return the value of the property onShown.
     */
    public final EventHandler<Event> getOnShown() {

        return this.getWrappedControl().getOnShown();
    }

    /**
     * Called just prior to the ComboBox popup/display being hidden.
     *
     * @return the onHiding property.
     */
    public final ObjectProperty<EventHandler<Event>> onHidingProperty() {

        return this.getWrappedControl().onHidingProperty();
    }

    /**
     * Sets the value of the property onHiding.
     *
     * @param value
     *            the value of the property onHiding.
     */
    public final void setOnHiding(final EventHandler<Event> value) {

        this.getWrappedControl().setOnHiding(value);
    }

    /**
     * Gets the value of the property onHiding.
     *
     * @return the value of the property onHiding.
     */
    public final EventHandler<Event> getOnHiding() {

        return this.getWrappedControl().getOnHiding();
    }

    /**
     * Called just after the ComboBoxBase popup/display has been hidden.
     *
     * @return the onHidden property.
     */
    public final ObjectProperty<EventHandler<Event>> onHiddenProperty() {

        return this.getWrappedControl().onHiddenProperty();
    }

    /**
     * Sets the value of the property onHidden.
     *
     * @param value
     *            the value of the property onHidden.
     */
    public final void setOnHidden(final EventHandler<Event> value) {

        this.getWrappedControl().setOnHidden(value);
    }

    /**
     * Gets the value of the property onHidden.
     *
     * @return the value of the property onHidden.
     */
    public final EventHandler<Event> getOnHidden() {

        return this.getWrappedControl().getOnHidden();
    }

    /**
     * Requests that the ComboBox display the popup aspect of the user
     * interface. As mentioned in the ComboBoxBase class javadoc, what is
     * actually shown when this method is called is undefined, but commonly it
     * is some form of popup or dialog window.
     */
    public void show() {

        this.getWrappedControl().show();
    }

    /**
     * Closes the popup / dialog that was shown when show() was called.
     */
    public void hide() {

        this.getWrappedControl().hide();
    }

    /**
     * Arms the ComboBox. An armed ComboBox will show a popup list on the next
     * expected UI gesture.
     */
    public void arm() {

        this.getWrappedControl().arm();
    }

    /**
     * Disarms the ComboBox.
     *
     * @see #arm
     */
    public void disarm() {

        this.getWrappedControl().disarm();
    }


}
