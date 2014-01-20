%creating lists assignemnt.

-module(create).

-export([create/1, rc/1]).

%Create a list given N, i.e. create(3), List = 1,2,3 etc..


create(M)->
    c(1, M).

c(X, M) when X < M ->
    [X | c(X + 1, M)];
c(M, M) -> [M].


rc(X) when X > 0 ->
    [X | rc(X-1)];
rc(0) -> [].

