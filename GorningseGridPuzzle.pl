groningse_grid(1, [click]).

groningse_grid(N, Clicks) :-
    create_grid(N, Grid),
    first_row_click_permutations(N, Clicks),
    grid_recursion(Clicks, Grid).

% color of squares
blue(blue).
red(red).

% create n size list of blue squares
n_size_list(N, List) :-
     length(List, N),
     maplist(=(blue), List).

% create n by n grid of blue squares
create_grid(N, Grid) :-
    length(Grid, N),
    n_size_list(N, List),
    maplist(=(List), Grid).

% used for permutations
click_state(noclick).
click_state(click).

% generate click permutations for first row
first_row_click_permutations(0,[]) :- !.
first_row_click_permutations(N, [H|T]) :-
  click_state(H),
  % go through list of lists
  N1 is N - 1,
  first_row_click_permutations(N1, T).

% used to flip squares on click
flip(blue, red).
flip(red, blue).

% predicate that takes a list of click/noclicks and a list of blue/reds, 
% and yields a new list of blue/reds 
% representing a single row that has been clicked

% if click is the first square, 
% then recursively calls flip_first_row2 for the rest of the list
flip_first_row([click|Clicks_Tail], [H1, H2|Color_Tail], 
               New_Color_List) :-
    flip(H1, X),
    flip(H2, Y),
    flip_first_row2(Clicks_Tail, [X, Y|Color_Tail], New_Color_List).

% if noclick is the first square,
% then recursively calls flip_first_row2 for the rest of the list
flip_first_row([noclick|Clicks_Tail], Color_List, 
               New_Color_List) :-
    flip_first_row2(Clicks_Tail, Color_List, New_Color_List).

% if click is not the last square
flip_first_row2([click|Clicks_Tail], [H1, H2, H3|Color_Tail], 
                [H4, H5, H6|New_Color_Tail]) :-
  flip(H1, H4),
  flip(H2, X),
  flip(H3, Y),
  flip_first_row2(Clicks_Tail, [X, Y|Color_Tail], [H5, H6|New_Color_Tail]).

% if noclick is not the last square
flip_first_row2([noclick|Clicks_Tail], [H|Color_List], 
                [H|New_Color_Tail]) :-
  flip_first_row2(Clicks_Tail, Color_List, New_Color_Tail).

% if click is the last square
flip_first_row2([click], [H1, H2], [H3, H4]) :-
  flip(H1, H3),
  flip(H2, H4).

% if noclick is the last square
flip_first_row2([noclick], Color_List, Color_List).

% flips the squares that are below an already flipped square
% predicate takes in a list of clicks from the row above

% when the above square is click, we flip the square below it
flip_next_row([], [], []) :- !.
flip_next_row([click|Clicks_Tail], [H1|Color_Tail], [H2|New_Color_Tail]) :-
  flip(H1, H2),
  flip_next_row(Clicks_Tail, Color_Tail, New_Color_Tail).

% when the above square is noclick, 
% we set the color of the square below it to the same color
flip_next_row([], [], []) :- !.
flip_next_row([noclick|Clicks_Tail], [H1|Color_Tail], [H1|New_Color_Tail]) :-
  flip_next_row(Clicks_Tail, Color_Tail, New_Color_Tail).

% predicate that takes the colors from the row above 
% to create clicks list for next row

% if the square above is blue, we want to click
next_row_clicks([], []) :- !.
next_row_clicks([blue|Color_Tail], [click|Clicks_Tail]) :-
  next_row_clicks(Color_Tail, Clicks_Tail).

% if the square above is red, we want to noclick
next_row_clicks([], []) :- !.
next_row_clicks([red|Color_Tail], [noclick|Clicks_Tail]) :-
  next_row_clicks(Color_Tail, Clicks_Tail).

% generate-and-test approach means lastly including a test predicate
% to see if the last row is all red
% if so, then success
last_row_test([]).
last_row_test([H|T]) :-
    red(H),
    last_row_test(T).

% predicate takes in list of first row clicks and
% a list of blue/red rows, and a list of click lists
% recurses down the grid to the last row
% it then tests the last row to see if all squares are red

% base case
grid_recursion(Clicks, [H]) :-
    flip_first_row(Clicks, H, Row),
    last_row_test(Row).

% recursive case
grid_recursion(First_Row_Clicks, [H1, H2|T]) :-
  % first we flip the first row squares and get a list of new colors squares
  flip_first_row(First_Row_Clicks, H1, New_Color_Squares),
  % takes in the first row clicks to see what squares to flip on the next row
  % yields a new color list for next row = X
  flip_next_row(First_Row_Clicks, H2, X),
  % takes in first row after squares have been flipped 
  % to generate clicks list for the next row
  next_row_clicks(New_Color_Squares, Next_Row_Clicks),
  grid_recursion(Next_Row_Clicks, [X|T]).