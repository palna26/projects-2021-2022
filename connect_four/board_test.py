from board import Board

params = {'num_row': 4, 'num_col': 4, 'cell_size': 100}


def test_constructor():
    # Test minimal required constructor args
    b = Board(params['num_row'], params['num_col'], params['cell_size'])
    assert b.num_row == 4 and b.num_col == 4 and b.cell_size == 100


def test_initiate_board():
    # Test initiate board function
    b = Board(params['num_row'], params['num_col'], params['cell_size'])
    b.initiate_board()
    assert b.token_location == [[(150.0, 50.0), (150.0, 150.0), (150.0, 250.0), (150.0, 350.0)], [(250.0, 50.0), (250.0, 150.0), (250.0, 250.0), (250.0, 350.0)], [(350.0, 50.0), (350.0, 150.0), (350.0, 250.0), (350.0, 350.0)], [(450.0, 50.0), (450.0, 150.0), (450.0, 250.0), (450.0, 350.0)], [
        (150.0, 50.0), (150.0, 150.0), (150.0, 250.0), (150.0, 350.0)], [(250.0, 50.0), (250.0, 150.0), (250.0, 250.0), (250.0, 350.0)], [(350.0, 50.0), (350.0, 150.0), (350.0, 250.0), (350.0, 350.0)], [(450.0, 50.0), (450.0, 150.0), (450.0, 250.0), (450.0, 350.0)]]
    assert b.placed_tokens == [[None, None, None, None], [None, None, None, None], [None, None, None, None], [
        None, None, None, None], [None, None, None, None], [None, None, None, None], [None, None, None, None], [None, None, None, None]]

def test_drop_token():
    # Test drop token function
    b = Board(params['num_row'], params['num_col'], params['cell_size'])
    token = b.drop_token('Red', 50, 150) 
    assert token.color == 'Red' and token.x == 50.0 and token.y == 50.0 and token.target_y == 450.0
    token = b.drop_token('Red', 50, 150)
    assert token.color == 'Red' and token.x == 50.0 and token.y == 50.0 and token.target_y == 350.0
    token = b.drop_token('Red', 150, 150)
    assert token.color == 'Red' and token.x == 150.0 and token.y == 50.0 and token.target_y == 450.0

def test_check_board_full():
    # Test check board full function
    b = Board(params['num_row'], params['num_col'], params['cell_size'])
    b.token_count = 16
    assert b.check_board_full()
    b.token_count = 14
    assert b.check_board_full() is False
