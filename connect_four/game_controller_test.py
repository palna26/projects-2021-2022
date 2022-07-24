from game_controller import GameController

params = {'row': 4, 'column': 4, 'cell_size': 100}


def test_constructor():
    # Test minimal required constructor args
    gc = GameController(params['row'], params['column'], params['cell_size'])
    assert gc.cell_size == 100 and gc.row == 4 and gc.column == 4 and hasattr(gc, 'is_player_turn') and hasattr(gc, 'is_comp_turn') and hasattr(gc, 'board') and hasattr(gc, 
        'countdown') and hasattr(gc, 'current_computer_token') and hasattr(gc, 'player_wins') and hasattr(gc, 'computer_wins') and hasattr(gc, 'player_name') and hasattr(gc, 'ask_user')

def test_player_move():
    # Test player move function
    gc = GameController(params['row'], params['column'], params['cell_size'])
    gc.player_move(50, 150)
    assert gc.board.token_count == 1 and gc.countdown == 100 and gc.is_player_turn == False and gc.is_comp_turn == True

def test_computer_move():
    # Test computer move function
    gc = GameController(params['row'], params['column'], params['cell_size'])
    gc.is_player_turn = False
    gc.countdown = 1
    gc.computer_move()
    assert gc.countdown == 0
    gc.is_comp_turn = True
    gc.computer_move()
    assert gc.board.token_count == 1 and gc.is_comp_turn is False and gc.is_player_turn is False
    gc.current_computer_token.dropped = True
    gc.computer_move()
    assert gc.is_player_turn is True


def test_check_game_over():
    # Test check game over function
    gc = GameController(params['row'], params['column'], params['cell_size'])
    gc.check_game_over()
    assert gc.game_over is False
    gc.board.token_count = 16
    gc.check_game_over()
    assert gc.game_over is True
