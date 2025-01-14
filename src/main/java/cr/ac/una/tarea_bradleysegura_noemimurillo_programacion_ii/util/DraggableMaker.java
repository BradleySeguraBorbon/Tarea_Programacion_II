/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller.AccountOpeningController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Bradley
 */
public class DraggableMaker {

    private double insideMouseX, insideMouseY;

    public void makeDraggable(Node draggableNode, Pane externalContainer, Pane internalContainerA, Pane internalContainerB) {

        draggableNode.setOnMousePressed(mouseEvent -> {
            insideMouseX = mouseEvent.getX();
            insideMouseY = mouseEvent.getY();

            if (internalContainerA.getChildren().contains(draggableNode)) {
                internalContainerA.getChildren().remove(draggableNode);
                externalContainer.getChildren().add(draggableNode);
                draggableNode.setLayoutX((mouseEvent.getSceneX() - 140) - insideMouseX);
                draggableNode.setLayoutY((mouseEvent.getSceneY() - 110) - insideMouseY);
            } else if (internalContainerB.getChildren().contains(draggableNode)) {
                internalContainerB.getChildren().remove(draggableNode);
                externalContainer.getChildren().add(draggableNode);
                draggableNode.setLayoutX((mouseEvent.getSceneX() - 140) - insideMouseX);
                draggableNode.setLayoutY((mouseEvent.getSceneY() - 110) - insideMouseY);
            }
        });

        draggableNode.setOnMouseDragged(mouseEvent -> {
            draggableNode.setLayoutX((mouseEvent.getSceneX() - 140) - insideMouseX); //vbox izq . getWidth()
            draggableNode.setLayoutY((mouseEvent.getSceneY() - 110) - insideMouseY); // hbox arr . getHeight ()
        });

        draggableNode.setOnMouseReleased(mouseEvent -> {
            if (externalContainer.getChildren().contains(draggableNode)) {
                Affiliated selectedAffiliated = (Affiliated) AppContext.getInstance().get("selectedAffiliated");
                AccountOpeningController accountOpController = (AccountOpeningController) FlowController.getInstance().getController("AccountOpeningView");
                System.out.println("AnchorPane contains Draggable Node");
                if (draggableNode.getLayoutX() <= externalContainer.getWidth() / 2) {
                    if (selectedAffiliated.isAccountRemovable(((Label) draggableNode).getText())) {
                        externalContainer.getChildren().remove(draggableNode);
                        internalContainerA.getChildren().add(draggableNode);
                        accountOpController.removeAccount(draggableNode);
                        System.out.println("Draggable Node intersects first half");
                        return;
                    } else {
                        new Mensaje().show(Alert.AlertType.ERROR, "CUENTA NO REMOVIBLE", "La cuenta seleccionada posee fondos. Para deshabilitarla, primero retira tu dinero");
                    }
                }
                externalContainer.getChildren().remove(draggableNode);
                internalContainerB.getChildren().add(draggableNode);
                accountOpController.createAccount(draggableNode);
                System.out.println("Draggable Node intersects second half");
            }
        });
    }
}
