from board import Board
from computer import Computer
import operator


class GameController:
  """
  Maintains the state of the game
  and manages interactions of game elements.
  """

  def __init__(self, row, column, cell_size):
    """Draws token"""
    self.cell_size = cell_size
    self.row = row
    self.column = column
    self.is_player_turn = True
    self.is_comp_turn = False
    self.board = Board(row, column, cell_size)
    self.game_over = False
    self.computer = Computer(self.board, row, column)
    self.countdown = 0
    self.current_computer_token = None
    self.player_wins = False
    self.computer_wins = False
    self.player_name = None
    self.ask_user = False
  
  def input(self, message=''):
    from javax.swing import JOptionPane
    return JOptionPane.showInputDialog(frame, message)

  def save_score(self):
    """Updates player name and score in file"""
    if self.ask_user:
      self.player_name = self.input("Enter you name here:")
      name_score = {}
      with open("scores.txt", "r") as f:
        for line in f.readlines():
          line = line.strip().split(' ')
          name_score[line[0]] = int(line[1])
      try:
        name_score[self.player_name] += 1
      except KeyError:
        name_score[self.player_name] = 1
      name_score = sorted(name_score.items(), key=operator.itemgetter(1),reverse=True)
      with open("scores.txt", "w") as f:
        for touple in name_score:
          line = touple[0] + " " + str(touple[1]) + "\n"
          f.write(line)
        f.close()

  def draw(self):
    """Draws a board"""
    self.board.draw_board()
    self.game_is_over()

  def do_prepare_to_drop(self, mouseX, mouseY):
    """Takes arguments to draw player token at top of board"""
    self.check_game_over()
    if not self.game_over:
      self.board.prepare_to_drop('Red', mouseX, mouseY)
  
  def player_move(self, mouseX, mouseY):
    """Drops red token onto the board from user"""
    # Ensures player is making a valid move before executing
    if self.board.drop_token('Red', mouseX, mouseY) is not None:
        self.board.token_count += 1
        self.countdown = 100
        self.is_player_turn = False
        self.is_comp_turn = True

  def computer_move(self):
    """Uses AI to drop red yellow token onto the board"""
    # Countdown is used to lag computer move after player move
    if self.countdown > 0:
      self.countdown -= 1
    elif self.countdown == 0 and self.is_comp_turn:
      self.board.token_count += 1
      coordinates = self.computer.make_computer_move()
      self.current_computer_token = self.board.drop_token('Yellow', coordinates[1], coordinates[0])
      # Ensure computer makes a valid move and not in a full column
      while self.current_computer_token is None:
        coordinates = self.computer.make_computer_move()
        self.current_computer_token = self.board.drop_token('Yellow', coordinates[1], coordinates[0])
      self.is_comp_turn = False
    # Does not player make a move until the computer token has finished dropping
    elif self.current_computer_token is not None and self.current_computer_token.dropped:
      self.is_player_turn = True

  def check_game_over(self):
    """Checks if game is over"""
    for row in self.board.placed_tokens:
      for token in row:
        if token is not None and not token.dropped:
          self.game_over = False
          return
    if self.board.check_board_full():
      print("Game Over")
      self.game_over = True
    else:
      # Check horizontal win
      for row in range(self.board.num_row):
        yellow_count = 0
        red_count = 0
        for col in range(self.board.num_col):
          current_token = self.board.placed_tokens[row][col]
          if current_token is not None:
            if current_token.color == 'Red':
              red_count += 1
              yellow_count = 0
              if red_count == 4:
                print("Game Over, Red Wins")
                self.player_wins = True
                self.game_over = True
            else:
              yellow_count += 1
              red_count = 0
              if yellow_count == 4:
                print("Game Over, Yellow Wins")
                self.computer_wins = True
                self.game_over = True
          else:
            yellow_count = 0
            red_count = 0
      # Check vertical win
      for col in range(self.board.num_col):
        yellow_count = 0
        red_count = 0
        for row in range(self.board.num_row):
          current_token = self.board.placed_tokens[row][col]
          if current_token is not None:
            if current_token.color == 'Red':
              red_count += 1
              yellow_count = 0
              if red_count == 4:
                print("Game Over, Red Wins")
                self.player_wins = True
                self.game_over = True
            else:
              yellow_count += 1
              red_count = 0
              if yellow_count == 4:
                print("Game Over, Yellow Wins")
                self.computer_wins = True
                self.game_over = True
      # Check positive slope win
      for row in range(self.board.num_row - 3):
        for col in range(self.board.num_col - 3):
          first_token = self.board.placed_tokens[row][col]
          second_token = self.board.placed_tokens[row+1][col+1]
          third_token = self.board.placed_tokens[row+2][col+2]
          fourth_token = self.board.placed_tokens[row+3][col+3]
          if first_token is not None and second_token is not None and third_token is not None and fourth_token is not None:
            if first_token.color == 'Red' and second_token.color == 'Red' and third_token.color == 'Red' and fourth_token.color == 'Red':
                print("Game Over, Red Wins")
                self.player_wins = True
                self.game_over = True
            elif first_token.color == 'Yellow' and second_token.color == 'Yellow' and third_token.color == 'Yellow' and fourth_token.color == 'Yellow':
                print("Game Over, Yellow Wins")
                self.computer_wins = True
                self.game_over = True
      # Check negative slope win
      for row in range(self.board.num_row - 3):
        for col in range(self.board.num_col - 3):
          first_token = self.board.placed_tokens[row][col]
          second_token = self.board.placed_tokens[row+1][col-1]
          third_token = self.board.placed_tokens[row+2][col-2]
          fourth_token = self.board.placed_tokens[row+3][col-3]
          if first_token is not None and second_token is not None and third_token is not None and fourth_token is not None:
            if first_token.color == 'Red' and second_token.color == 'Red' and third_token.color == 'Red' and fourth_token.color == 'Red':
                print("Game Over, Red Wins")
                self.player_wins = True
                self.game_over = True
            elif first_token.color == 'Yellow' and second_token.color == 'Yellow' and third_token.color == 'Yellow' and fourth_token.color == 'Yellow':
                print("Game Over, Yellow Wins")
                self.computer_wins = True
                self.game_over = True

  def game_is_over(self):
    """Print statements for game over"""
    if self.game_over:
      if self.player_wins:
        fill(255, 0, 0)
        textSize(30)
        textAlign(CENTER, CENTER)
        text("GAME OVER! RED WIN!!", (self.cell_size*self.column)/2, (self.cell_size+self.cell_size*self.row)/2)
        self.ask_user = True
      elif self.computer_wins:
        fill(255, 204, 0)
        textSize(30)
        textAlign(CENTER, CENTER)
        text("GAME OVER! YELLOW WIN!!", (self.cell_size*self.column)/2, (self.cell_size+self.cell_size*self.row)/2)
      else:
        fill(255, 0, 0)
        textSize(30)
        textAlign(CENTER, CENTER)
        text("GAME OVER! NO ONE WINS!", (self.cell_size*self.column)/2, (self.cell_size+self.cell_size*self.row)/2)


# if self.player_wins:
#             fill(255, 0, 0)
#             textSize(30)
#             textAlign(CENTER, CENTER)
#             score_text = "You scored %s" % self.player_score
#             text("GAME OVER! YOU WIN!!!\n" + score_text,
#                  self.WIDTH/2, self.HEIGHT/2)
#             self.ask_user = True