%Lists - Advanced list manipulation.

-module(lists).

-export([filter/2]).

filter([], 0)-> [];
filter([], X)->
    X.
