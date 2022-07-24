CELL_SIZE = 100

class GameToken:
    """
    A single Token
    """

    def __init__(self, color, x, y):
        """Constructor to set up the Token class"""
        self.color = color
        if self.color == 'Red':
            self.token_color = (255, 0, 0)
        else:
            self.token_color = (255, 255, 0)
        self.x = x
        self.y = CELL_SIZE/2
        self.token_size = 100
        self.radius = self.token_size/2
        self.y_vel = 10
        self.dropped = False
        self.target_y = y

    def draw_token(self):
        """Draws token"""
        noStroke()
        fill(*self.token_color)
        ellipse(self.x, self.y, self.radius*2, self.radius*2)


    def animate_drop_token(self):
        """Updates the y coordinate of dropping token to target y"""
        if not self.dropped:
            if (self.y < self.target_y):
                self.y += self.y_vel 
            else:
                self.dropped = True