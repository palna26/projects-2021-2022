from game_controller import GameController

ROWS = 6
COLUMNS = 7
CELL_SIZE = 100
BOARD_WIDTH = CELL_SIZE*(COLUMNS)
BOARD_HEIGHT = CELL_SIZE+CELL_SIZE*ROWS

gc = GameController(ROWS, COLUMNS, CELL_SIZE)


def setup():
    size(BOARD_WIDTH, BOARD_HEIGHT)
    colorMode(RGB, 1)


def draw():
    background(0.8)
    gc.check_game_over()
    if gc.game_over is False:
        # Draws token at top column on mouse pressed
        if gc.is_player_turn and (mousePressed):
            gc.do_prepare_to_drop(mouseX, mouseY)

        if gc.is_player_turn is False:
            gc.computer_move()
    gc.draw()
    if gc.game_over is True:
        noLoop()
        gc.save_score()



def mouseReleased():
    # Drops token on mouse released
    gc.check_game_over()
    if gc.is_player_turn and gc.game_over is False:
        gc.player_move(mouseX, mouseY)
