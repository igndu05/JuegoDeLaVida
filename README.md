ESPAÑOL 🇪🇸
Este es un programa sencillo, se trata del juego de la vida recreado en el lenguaje de programación Java.

Para quien no sepa de qué se trata el juego de la vida, es muy simple:
Se trata de un juego de cero jugadores, lo que quiere decir que su evolución está determinada por el estado inicial y no necesita ninguna entrada de datos posterior. El "tablero de juego" es una malla plana formada por cuadrados (las "células") 
que se extiende por el infinito en todas las direcciones. Por tanto, cada célula tiene 8 células "vecinas", que son las que están próximas a ella, incluidas las diagonales. Las células tienen dos estados: están "vivas" o "muertas" (o "encendidas" y "apagadas"). 
El estado de las células evoluciona a lo largo de unidades de tiempo discretas (se podría decir que por turnos). El estado de todas las células se tiene en cuenta para calcular el estado de las mismas al turno siguiente. Todas las células se actualizan simultáneamente 
en cada turno, siguiendo estas reglas:
- Nace: Si una célula muerta tiene exactamente 3 células vecinas vivas "nace" (es decir, al turno siguiente estará viva).
- Muere: una célula viva puede morir por uno de 2 casos:
- Sobrepoblación: si tiene más de tres vecinos alrededor.
- Aislamiento: si tiene solo un vecino alrededor o ninguno.
- Vive: una célula se mantiene viva si tiene 2 o 3 vecinos a su alrededor.

Sabiendo esto, decidimos recrear este juego en Java, el cuál dispone de un menú con el que podremos crear una partida o cargar una ya existente importando un archivo de texto que siga unos parámetros en concreto.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

ENGLISH 🇬🇧
This is a simple program; it's the Game of Life recreated in the Java programming language.

For those unfamiliar with the Game of Life, it's very simple:
It is a zero-player game, meaning that its evolution is determined by the initial state and requires no subsequent data input. The "game board" is a flat grid made up of squares (the "cells") that extends to infinity in all directions. Therefore, each cell has 
8 "neighboring" cells, which are those close to it, including the diagonals. Cells have two states: they are "alive" or "dead" (or "on" and "off").
The state of cells evolves over discrete units of time (one could say in turns). The state of all cells is taken into account when calculating their state for the next turn. All cells are updated simultaneously
on each turn, following these rules:
- Born: If a dead cell has exactly three living neighbors, it is "born" (i.e., it will be alive the next turn).
- Dies: A living cell can die in one of two ways:
- Overcrowding: If it has more than three neighbors around it.
- Isolation: If it has only one neighbor around it or none at all.
- Lives: A cell stays alive if it has two or three neighbors around it.

Knowing this, we decided to recreate this game in Java, which has a menu that allows you to create a game or load an existing one by importing a text file that follows specific parameters.
