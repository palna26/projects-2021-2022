from game_token import GameToken

CELL_SIZE = 100
params = {'color': 'Red', 'x': 50, 'y': 150}


def test_constructor():
    # Test minimal required constructor args
    t = GameToken(params['color'], params['x'], params['y'])
    assert t.color == 'Red' and t.token_color == (255, 0, 0) and t.x == 50 and t.y == 50 and hasattr(
        t, "token_size") and hasattr(t, "radius") and hasattr(t, "y_vel") and hasattr(t, "dropped") and t.target_y == 150
