<?xml version="1.0" encoding="UTF-8"?>

<!--
  Copyright (c) 2015, 2019, Gluon and/or its affiliates.
  All rights reserved. Use is subject to license terms.

  This file is available and licensed under the following license:

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions
  are met:

  - Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
  - Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in
    the documentation and/or other materials provided with the distribution.
  - Neither the name of Oracle Corporation nor the names of its
    contributors may be used to endorse or promote products derived
    from this software without specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
  OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->

<?import javafx.scene.Group?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.ScrollPane?>
<?import javafx.scene.control.SplitPane?>
<?import javafx.scene.effect.ColorAdjust?>
<?import javafx.scene.image.Image?>
<?import javafx.scene.image.ImageView?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.shape.Rectangle?>
<?import javafx.scene.text.Font?>

<VBox maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="609.0" prefWidth="944.0" scaleShape="false" xmlns="http://javafx.com/javafx/19" xmlns:fx="http://javafx.com/fxml/1" fx:controller.MenuFeedBack="controller.MenuController">
  <children>
    <SplitPane centerShape="false" dividerPositions="0.11587436332767402" focusTraversable="true" prefHeight="-1.0" prefWidth="-1.0" styleClass="window" VBox.vgrow="ALWAYS">
      <items>
        <AnchorPane maxWidth="-Infinity" minHeight="580.0" minWidth="-Infinity" prefHeight="580.0" prefWidth="100.0" styleClass="sidebar" SplitPane.resizableWithParent="false">
               <children>
                  <ImageView fitHeight="52.0" fitWidth="93.0" layoutX="9.0" layoutY="41.0" pickOnBounds="true" preserveRatio="true">
                     <image>
                        <Image url="@../figma/2rp.png" />
                     </image>
                  </ImageView>
                  <Button layoutX="28.0" layoutY="542.0" minWidth="46.0" mnemonicParsing="false" prefHeight="38.0" prefWidth="46.0" scaleX="0.5" scaleY="0.5" styleClass="rp-column" text="Button" />
                  <ImageView fitHeight="40.0" fitWidth="43.0" layoutX="36.0" layoutY="541.0" pickOnBounds="true" preserveRatio="true">
                     <image>
                        <Image url="@../figma/log.png" />
                     </image>
                  </ImageView>
               </children></AnchorPane>
        <ScrollPane prefHeight="-1.0" prefWidth="-1.0" SplitPane.resizableWithParent="false">
          <content>
            <AnchorPane id="Content" prefHeight="544.0" prefWidth="921.0" styleClass="content">
              <children>
                        <Group layoutX="41.0" layoutY="193.0" onMouseClicked="#irCadastroUsuario" AnchorPane.leftAnchor="41.0">
                           <children>
                              <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#2128bf1f" height="112.0" layoutY="-7.0" stroke="BLACK" strokeType="INSIDE" style="-fx-border-radious: 10px;" width="192.0" />
                              <ImageView fitHeight="54.0" fitWidth="65.0" layoutX="64.0" layoutY="22.0" pickOnBounds="true" preserveRatio="true">
                                 <image>
                                    <Image url="@../assets/Grafico.png" />
                                 </image>
                              </ImageView>
                              <Label layoutX="18.0" layoutY="76.0" text="Cadastro de Usuario" textFill="#1f25c1">
                                 <font>
                                    <Font name="Verdana Bold" size="14.0" />
                                 </font>
                              </Label>
                           </children>
                           <effect>
                              <ColorAdjust contrast="1.0" hue="0.02" saturation="1.0" />
                           </effect>
                        </Group>
                        <Label fx:id="homeText" layoutX="316.0" layoutY="35.0" styleClass="title" text="Seja bem vindo!" textFill="#1f25c1" AnchorPane.leftAnchor="316.0" AnchorPane.rightAnchor="301.0" AnchorPane.topAnchor="35.0">
                           <font>
                              <Font name="Calibri Bold" size="24.0" />
                           </font>
                        </Label>
                        <ImageView fitHeight="32.0" fitWidth="32.0" layoutX="759.0" layoutY="33.0" pickOnBounds="true" preserveRatio="true" AnchorPane.rightAnchor="124.0" AnchorPane.topAnchor="33.0">
                           <image>
                              <Image url="@../assets/Usuario.png" />
                           </image>
                        </ImageView>
                        <Group layoutX="316.0" layoutY="193.0" onMouseClicked="#irCadastroCr" AnchorPane.leftAnchor="316.0" AnchorPane.topAnchor="186.0">
                           <children>
                              <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#2128bf1f" height="112.0" layoutY="-7.0" stroke="BLACK" strokeType="INSIDE" width="192.0" />
                              <ImageView fitHeight="54.0" fitWidth="65.0" layoutX="64.0" layoutY="22.0" pickOnBounds="true" preserveRatio="true">
                                 <image>
                                    <Image url="@../assets/Grafico.png" />
                                 </image>
                              </ImageView>
                              <Label layoutX="32.0" layoutY="76.0" text="Cadastro de CRs" textFill="#1f25c1">
                                 <font>
                                    <Font name="Verdana Bold" size="14.0" />
                                 </font>
                              </Label>
                           </children>
                           <effect>
                              <ColorAdjust contrast="1.0" hue="0.02" saturation="1.0" />
                           </effect>
                        </Group>
                        <Group layoutX="589.0" layoutY="193.0" onMouseClicked="#irLancamentoHora">
                           <children>
                              <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#2128bf1f" height="112.0" layoutY="-7.0" stroke="BLACK" strokeType="INSIDE" width="192.0" />
                              <ImageView fitHeight="54.0" fitWidth="65.0" layoutX="64.0" layoutY="22.0" pickOnBounds="true" preserveRatio="true">
                                 <image>
                                    <Image url="@../assets/Grafico.png" />
                                 </image>
                              </ImageView>
                              <Label layoutX="11.0" layoutY="76.0" text="Lançamento de Horas" textFill="#1f25c1">
                                 <font>
                                    <Font name="Verdana Bold" size="14.0" />
                                 </font>
                              </Label>
                           </children>
                           <effect>
                              <ColorAdjust contrast="1.0" hue="0.02" saturation="1.0" />
                           </effect>
                        </Group>
                        <Group layoutX="41.0" layoutY="335.0" onMouseClicked="#irCadastroCliente">
                           <children>
                       
                              <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#2128bf1f" height="112.0" stroke="BLACK" strokeType="INSIDE" style="-fx-border-radious: 10px;" width="192.0" />
                              <ImageView fitHeight="54.0" fitWidth="65.0" layoutX="64.0" layoutY="20.0" pickOnBounds="true" preserveRatio="true">
                                 <image>
                                    <Image url="@../assets/Grafico.png" />
                                 </image>
                              </ImageView>
                              <Label layoutX="19.0" layoutY="80.0" text="Cadastro de Cliente" textFill="#1f25c1">
                                 <font>
                                    <Font name="Verdana Bold" size="14.0" />
                                 </font>
                              </Label>
                           </children>
                           
                           <effect>
                              <ColorAdjust contrast="1.0" hue="0.02" saturation="1.0" />
                           </effect>
                        </Group>
                        <Group layoutX="316.0" layoutY="342.0" onMouseClicked="#irControleCr">
                           <children>
                              <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#2128bf1f" height="112.0" layoutY="-7.0" stroke="BLACK" strokeType="INSIDE" width="192.0" />
                              <ImageView fitHeight="54.0" fitWidth="65.0" layoutX="64.0" layoutY="22.0" pickOnBounds="true" preserveRatio="true">
                                 <image>
                                    <Image url="@../assets/Grafico.png" />
                                 </image>
                              </ImageView>
                              <Label layoutX="18.0" layoutY="76.0" text="Visualização de CRs" textFill="#1f25c1">
                                 <font>
                                    <Font name="Verdana Bold" size="14.0" />
                                 </font>
                              </Label>
                           </children>
                           <effect>
                              <ColorAdjust contrast="1.0" hue="0.02" saturation="1.0" />
                           </effect>
                        </Group>
                        <Group layoutX="589.0" layoutY="342.0" onMouseClicked="#irControleCrUsuario">
                           <children>
                              <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#2128bf1f" height="112.0" layoutY="-7.0" stroke="BLACK" strokeType="INSIDE" width="192.0" />
                              <ImageView fitHeight="54.0" fitWidth="65.0" layoutX="64.0" layoutY="22.0" pickOnBounds="true" preserveRatio="true">
                                 <image>
                                    <Image url="@../assets/Grafico.png" />
                                 </image>
                              </ImageView>
                              <Label layoutX="18.0" layoutY="76.0" text="Controle Cr - Usuario" textFill="#1f25c1">
                                 <font>
                                    <Font name="Verdana Bold" size="14.0" />
                                 </font>
                              </Label>
                           </children>
                           <effect>
                              <ColorAdjust contrast="1.0" hue="0.02" saturation="1.0" />
                           </effect>
                        </Group>



                        
                     </children>
                     
            </AnchorPane>
          </content>
        </ScrollPane>
      </items>
    </SplitPane>
  </children>
</VBox>
