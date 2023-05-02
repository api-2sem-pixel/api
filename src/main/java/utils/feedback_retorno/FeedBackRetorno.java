	public class FeedBackRetorno {
    
    public static void motivo() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Motivo");
    dialog.setHeaderText("Por favor, digite seus motivos:");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(result.get());
        alert.show();
    }
}
