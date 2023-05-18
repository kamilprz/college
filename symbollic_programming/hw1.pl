
numeral(0).
numeral(-X) :- numeral(X).
numeral(s(X)) :- numeral(X).
numeral(p(X)) :- numeral(X).
numeral(X + Y) :- numeral(X), numeral(Y).
numeral(X-Y) :- numeral(X), numeral(Y).

% ------------------------------------------------------------------------------

add2(X1, Y1 + Y2, Z) :- add2(Y1, Y2, Y3), add2(X1, Y3, Z).
add2(X1 + X2, Y, Z) :- add2(X1,X2,X3), add2(X3, Y, Z).
add2(X1 + X2, Y1 + Y2, Z) :- add2(X1, X2, X3), add2(Y1, Y2, Y3), add2(X3, Y3, Z).

add2(X1, Y1 - Y2, Z) :- subtract(Y1, Y2, Y3), add2(X1, Y3, Z).
add2(X1 - X2, Y1, Z) :- subtract(X1, X2, X3), add2(X3, Y1, Z).
add2(X1 - X2, Y1 - Y2, Z) :- subtract(X1, X2, X3), subtract(Y1, Y2, Y3), add2(X3, Y3, Z).

add2(-X, Y, Z) :- minus(X, X1), add2(X1 ,Y, Z).
add2(-p(X), Y, Z) :- minus(p(X), X1), add2(X1 ,Y, Z).
add2(-s(X), Y, Z) :- minus(s(X), X1), add2(X1 ,Y, Z).
add2(X, -Y, Z) :- minus(Y, Y1), add2(X, Y1, Z).
add2(X, -p(Y), Z) :- minus(p(Y), Y1), add2(X, Y1, Z).
add2(X, -s(Y), Z) :- minus(s(Y), Y1), add2(X, Y1, Z).

add2(s(0), p(0), 0).
add2(p(0), s(0), 0).

add2(s(p(X)), Y, Z) :- add2(X, Y, Z).
add2(X, s(p(Y)), Z) :- add2(X, Y, Z).
add2(p(s(X)), Y, Z) :- add2(X, Y, Z).
add2(X, p(s(Y)), Z) :- add2(X, Y, Z).

add2(s(X), p(Y), Z) :- add2(X, Y, Z).
add2(p(X), s(Y), Z) :- add2(X, Y, Z).
add2(s(X),Y,s(Z)) :- add2(X,Y,Z).
add2(X, s(Y),s(Z)) :- add2(X, Y, Z).
add2(p(X), Y, p(Z)) :- add2(X, Y, Z).
add2(X, p(Y), p(Z)) :- add2(X, Y, Z).

add2(0,X,X).

% ------------------------------------------------------------------------------

subtract(X1, Y1 + Y2, Z) :- add2(Y1, Y2, Y3), subtract(X1, Y3, Z).
subtract(X1 + X2, Y, Z) :- add2(X1, X2, X3), subtract(X3, Y, Z).
subtract(X1 + X2, Y1 + Y2, Z) :- add2(X1, X2, X3), add2(Y1, Y2, Y3), subtract(X3, Y3, Z).

subtract(X1, Y1 - Y2, Z) :- subtract(Y1, Y2, Y3), subtract(X1, Y3, Z).
subtract(X1 - X2, Y1, Z) :- subtract(X1, X2, X3), subtract(X3, Y1, Z).
subtract(X1 - X2, Y1 - Y2, Z) :- subtract(X1, X2, X3), subtract(Y1, Y2, Y3), subtract(X3, Y3, Z).

subtract(X, Y, Z) :- minus(Y, Y1), add2(X, Y1, Z).
subtract(X, p(Y), Z) :- minus(p(Y), Y1), add2(X, Y1, Z).
subtract(-X, Y, Z) :- minus(X, X1), minus(Y, Y1), add2(X1 ,Y1, Z).
subtract(X, -Y, Z) :- add2(X, Y, Z).

% ------------------------------------------------------------------------------

minus(s(p(0)), 0).
minus(p(s(0)), 0).
minus(X1 + X2, Z) :- add2(X1, X2, X3), minus(X3, Z).
minus(X1 - X2, Z) :- subtract(X1, X2, X3), minus(X3, Z).
minus(s(X), p(Z)) :- minus(X, Z).
minus(p(X), s(Z)) :- minus(X, Z).
minus(0,0).

