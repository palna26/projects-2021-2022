import random


class Computer:
    """
    A semi smart AI to play the game
    """

    def __init__(self, board, row, col):
        """Constructor to set up the Computer class"""
        self.board = board
        self.row = row
        self.col = col

    def make_computer_move(self):
        """Returns the coordinates to place the computer yellow token"""
        # Find horizontal place to correctly place yellow
        for row in range(self.board.num_row):
            for col in range(self.board.num_col - 3):
                candidates = list(self.board.placed_tokens[row][col:col+4])
                idx = self.check_candidates(candidates)
                if idx >= 0:
                    return self.board.token_location[row][col+idx]
        # Find vertical place to correctly place yellow
        for col in range(self.board.num_col):
            for row in range(self.board.num_row - 3):
                candidates = []
                for i in range(4):
                    candidates.append(self.board.placed_tokens[row+i][col])
                idx = self.check_candidates(candidates)
                if idx >= 0:
                    return self.board.token_location[row+idx][col]

        # Find positive slope place to correctly place yellow
        for col in range(self.board.num_col-3):
            for row in range(self.board.num_row - 3):
                candidates = []
                for i in range(4):
                    candidates.append(self.board.placed_tokens[row+i][col+i])
                idx = self.check_candidates(candidates)
                if idx >= 0:
                    return self.board.token_location[row+idx][col+idx]

        return self.generate_random_location()

    def check_candidates(self, candidates):
        """Returns the index of the list that is None"""
        # This function is looking at any 4 spaces and then making sure that 3 are yellow tokens and 1 is and open spot or 'None'
        none_count = 0
        for candidate in candidates:
            if candidate is None:
                none_count += 1
        # If there are four locations where 3 tokens are filled and 1 spot is None
        if none_count == 1 and len(candidates) == 4:
            found_red_token = False
            for i in range(4):
                # If any of the 3 filled locations is a Red token, immediately break out and return -1
                if candidates[i] is not None and candidates[i].color == 'Red':
                    found_red_token = True
            if not found_red_token:
                # Returns the index number in the list that is None
                return candidates.index(None)
        return -1

    def generate_random_location(self):
        """Randomly generates two x and y coordinates for the computer token"""
        row_destination = random.randint(0, self.row-1)
        col_destination = random.randint(0, self.col-1)
        return self.board.token_location[row_destination][col_destination]
