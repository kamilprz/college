
% Question 1

% Write a DCG that accepts strings of the form u2v where u and v are strings
% over the alphabet {0, 1} such that the number of 0’s in u is twice the number
% of 1’s in v. For example,
% | ?- s([0,1,0,1,2,0,0,1,0],L).
% L = [];
% L = [0];
% no

s --> [2].
s --> u, s, v.

u --> ones, [0], ones, [0], ones.
ones --> [].
ones --> [1], ones.

v --> zeros, [1], zeros.
zeros --> [].
zeros --> [0], zeros.


% Question 2

% Three neighbouring houses that all have a different colour, namely red,
% blue, and green. People of different nationalities live in the different
% houses and they all have a different pet.
% [Col1,Nat1,Pet1, Col2,Nat2,Pet2, Col3,Nat3,Pet3]

% **I changed s() to street() to avoid collision with question1
% | ?- street([red,english,snail, blue,japanese,jaguar, green,spanish,Z], []).
% Z = zebra;
% no

street -->  house(Col1,Nat1,Pet1), house(Col2,Nat2,Pet2), house(Col3,Nat3,Pet3),
			{Col1\=Col2}, {Col2\=Col3}, {Col1\=Col3},
			{Nat1\=Nat2}, {Nat2\=Nat3}, {Nat1\=Nat3},
			{Pet1\=Pet2}, {Pet2\=Pet3}, {Pet1\=Pet3}.

house(Colour, Nation, Pet) --> [Colour],{lex(Colour, col)}, [Nation],{lex(Nation, nat)}, [Pet],{lex(Pet, pet)}.

lex(red, col).
lex(green, col).
lex(blue, col). 

lex(english, nat).
lex(japanese, nat).
lex(spanish, nat).

lex(snail, pet).
lex(jaguar, pet).
lex(zebra, pet).


% Question 3

% Write a DCG that given a non-negative integer Sum, accepts lists of integers
% ≥ 1 that add up to Sum. For example,
% **I changed s() to sum() to avoid collision with question1
% | ?- sum(3,L,[]).
% L = [3];
% L = [2,1];
% L = [1,2];
% L = [1,1,1];
% no

sum(Nb)--> [Nb].
sum(Nb)--> [X], {mkList(Nb,L), member(X,L), R is Nb-X, R\=0}, sum(R).

mkList(1, [1]).
mkList(X, [X|List]) :- X > 0, X2 is X-1, mkList(X2, List).
