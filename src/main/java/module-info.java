module ec.edu.uees.spotifyapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.uees.spotifyapp to javafx.fxml;
    exports ec.edu.uees.spotifyapp;
}
