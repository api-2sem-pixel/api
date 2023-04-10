package utils.custom_cells;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.custom_types.DateTimePicker;

public class DateTimeCell<S> extends TableCell<S, LocalDateTime> {
        
    private final DateTimeFormatter formatter ;
    private final DateTimePicker dateTimePicker;
    
    public DateTimeCell() {
        
        formatter = DateTimeFormatter.ofPattern("MMMM d") ;
        dateTimePicker = new DateTimePicker() ;
        
        // Commit edit on Enter and cancel on Escape.
        // Note that the default behavior consumes key events, so we must 
        // register this as an event filter to capture it.
        // Consequently, with Enter, the datePicker's value won't yet have been updated, 
        // so commit will sent the wrong value. So we must update it ourselves from the
        // editor's text value.
        
        dateTimePicker.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                dateTimePicker.setValue(dateTimePicker.getConverter().fromString(dateTimePicker.getEditor().getText()));
                commitEdit(dateTimePicker.getValue().atStartOfDay());
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
        
        // Modify default mouse behavior on date picker:
        // Don't hide popup on single click, just set date
        // On double-click, hide popup and commit edit for editor
        // Must consume event to prevent default hiding behavior, so
        // must update date picker value ourselves.
        
        // Modify key behavior so that enter on a selected cell commits the edit
        // on that cell's date.
        
        dateTimePicker.setDayCellFactory(picker -> {
            DateCell cell = new DateCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                dateTimePicker.setValue(cell.getItem());
                if (event.getClickCount() == 2) {
                    dateTimePicker.hide();
                    commitEdit(cell.getItem().atStartOfDay());
                }
                event.consume();
            });
            cell.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    commitEdit(dateTimePicker.getValue().atStartOfDay());
                }
            });
            return cell ;
        });

        contentDisplayProperty().bind(Bindings.when(editingProperty())
                .then(ContentDisplay.GRAPHIC_ONLY)
                .otherwise(ContentDisplay.TEXT_ONLY));
    }
    
    @Override
    public void updateItem(LocalDateTime dateTime, boolean empty) {
        super.updateItem(dateTime, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(formatter.format(dateTime));
            setGraphic(dateTimePicker);
        }
    }
    
    @Override
    public void startEdit() {
        super.startEdit();
        if (!isEmpty()) {
            dateTimePicker.setValue(getItem().toLocalDate());
        }
    }

}