from game_token import GameToken


class Board:
    """
    A single game board
    """

    def __init__(self, num_row, num_col, cell_size):
        """Constructor to set up the Board class"""
        self.cell_size = cell_size
        self.num_row = num_row
        self.num_col = num_col
        self.placed_tokens = []
        self.token_location = []
        self.initiate_board()
        self.token_count = 0

    def draw_board(self):
        """Draws all placed tokens and board"""
        # Draw all placed tokens
        for row in range(self.num_row):
            for col in range(self.num_col):
                if self.placed_tokens[row][col] is not None:
                    self.placed_tokens[row][col].animate_drop_token()
                    self.placed_tokens[row][col].draw_token()

        BLUE = (0, 0, 255)
        STROKE_WEIGHT = 20

        fill(0)
        strokeWeight(STROKE_WEIGHT)
        stroke(*BLUE)

        # Darw the board based on calculations of row, column, and cell_size
        for column in range(0, self.num_col+1):
            line(column*self.cell_size, self.cell_size, column *
                 self.cell_size, (self.num_col+1)*self.cell_size)

        for row in range(1, self.num_row+2):
            line(0, row*self.cell_size, self.num_row *
                 self.cell_size+self.cell_size, row*self.cell_size)

    def initiate_board(self):
        """Takes two 2D arrays and fills one with null touples and the other with (x, y) coordinates of where token should be dropped"""
        for row in range(self.num_row):
            current_row = []
            placed_tokens_row = []
            for col in range(self.num_col):
                current_row.append((self.cell_size+row*(self.cell_size) +
                                    self.cell_size/2, self.cell_size/2+col*(self.cell_size)))
                placed_tokens_row.append(None)
            self.token_location.append(current_row)
            self.placed_tokens.append(placed_tokens_row)

    def drop_token(self, color, mouseX, mouseY):
        """Takes color and x and y arguments to add a single token to a list"""
        column = int(mouseX // self.cell_size)

        for row in range(self.num_row-1, -1, -1):
            if self.placed_tokens[row][column] == None:
                location = self.token_location[row][column]
                self.placed_tokens[row][column] = GameToken(
                    color, location[1], location[0])
                return self.placed_tokens[row][column]
        return None

    def prepare_to_drop(self, color, mouseX, mouseY):
        """Takes color and x and y arguments to draw a single token at the top of a column"""
        column = mouseX // self.cell_size
        for row in range(self.num_row-1, -1, -1):
            location = self.token_location[row][column]
            current_token = GameToken(color, location[1], 50)
        current_token.draw_token()

    def check_board_full(self):
        """Checks to see if all tokens are placed on board"""
        if self.token_count == self.num_row * self.num_col:
            return True
        return False
