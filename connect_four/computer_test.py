from computer import Computer
from board import Board

params = {'board': Board(4, 4, 100), 'row': 4, 'col': 4}


def test_constructor():
    # Test minimal required constructor args
    c = Computer(params['board'], params['row'], params['col'])
    assert c.board == params['board'] and c.row == 4 and c.col == 4

def test_make_computer_move_vertical_four_in_row():
    # Test make computer move function to see it place yellow token to make 4 in a row
    c = Computer(params['board'], params['row'], params['col'])
    c.board.drop_token('Yellow', 50, 150)
    c.board.drop_token('Yellow', 50, 150)
    c.board.drop_token('Yellow', 50, 150)
    assert c.make_computer_move() == (150.0, 50.0)

def test_make_computer_move_horizontal_four_in_row():
    # Test make computer move function to see it place yellow token to make 4 in a row
    c = Computer(params['board'], params['row'], params['col'])
    c.board.drop_token('Yellow', 50, 150)
    c.board.drop_token('Yellow', 150, 150)
    c.board.drop_token('Yellow', 250, 150)
    assert c.make_computer_move() == (450.0, 350.0)

def test_check_candidates():
    # Test check candidates function
    c = Computer(params['board'], params['row'], params['col'])
    candidates = [0, 1, 2, 3]
    assert c.check_candidates(candidates) == -1