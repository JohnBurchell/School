%Temp calculator

-module(temp).

-export([convert/1]).

f2c(F)->
    (F - 32) * 5 / 9.

c2f(C)->
    C * 9 / 5 + 32.

convert({c, Temp})->
    {f, c2f(Temp)};

convert({f, Temp})->
    {c, f2c(Temp)}.



