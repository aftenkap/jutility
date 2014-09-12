package org.jutility.javafx.control;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


/**
 * Panel for searching through other panel. Works off CTRL+F and filters through
 * list views to show only items that match search filter
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 1.0
 * 
 */
public class SearchPanel
        extends HBox {

    private StringProperty filterString;
    private Label          search;
    private TextField      searchBox;
    private Button         close;

    /**
     * constructor
     */
    public SearchPanel() {

        this.filterString = new SimpleStringProperty();
        this.setPadding(new Insets(5, 0, 0, 0));
        this.setSpacing(5);
        this.search = new Label("Find");

        this.search.minHeight(25);
        this.search.setFont(Font.font("Verdana", 14));
        this.searchBox = new TextField("");
        this.searchBox.setMinHeight(25);
        this.search.setLabelFor(this.searchBox);


        this.filterString.bindBidirectional(this.searchBox.textProperty());


        this.close = new Button("x");
        this.close.addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {

                        SearchPanel.this.searchBox.setText(null);
                        SearchPanel.this.setVisible(false);
                    }

                });
        this.getChildren().addAll(close, search, searchBox);


        this.searchBox.disableProperty().bind(this.disabledProperty());


        this.searchBox.requestFocus();

    }

    /**
     * Returns the filter string property.
     * 
     * @return the filter string property.
     */
    public StringProperty filterString() {

        return filterString;
    }

    /**
     * Returns the filter string.
     * 
     * @return the filter string.
     */
    public String getFilterString() {

        return filterString.get();
    }

    /**
     * Sets the filter string.
     * 
     * @param filterString
     *            the filter string.
     */
    public void setFilterString(String filterString) {

        this.filterString.set(filterString);
    }


    @Override
    public void requestFocus() {

        super.requestFocus();
        this.searchBox.requestFocus();
    }

}