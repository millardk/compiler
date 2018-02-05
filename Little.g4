grammar Little;

testrule        :  ;

fragment    UPPER   :   [A-Z]
;
fragment    LOWER   :   [a-z]
;
fragment    DIGIT   :   [0-9]
;
fragment    LETTER  :   UPPER | LOWER
;

INTLITERAL      :   DIGIT+
;
FLOATLITERAL    :   DIGIT ? '.' DIGIT+
;
STRINGLITERAL   :   '"'~["\n\r]*'"'
;

KEYWORD         :
    'PROGRAM'
|   'BEGIN'
|   'END'
|   'FUNCTION'
|   'READ'
|   'WRITE'
|   'IF'
|   'ELSE'
|   'FI'
|   'FOR'
|   'ROF'
|   'RETURN'
|   'INT'
|   'VOID'
|   'STRING'
|   'FLOAT'
|   'WHILE'
|   'ENDIF'
|   'ENDWHILE'
;

IDENTIFIER      :   LETTER (LETTER | DIGIT)*
;

OPERATOR        :
    ':='
|   '+'
|   '-'
|   '*'
|   '/'
|   '='
|   '!='
|   '<'
|   '>'
|   '('
|   ')'
|   ';'
|   ','
|   '<='
|   '>='
;

WHITESPACE      :   (' ' | '\t' | '\n' | '\r')+ ->skip
;

COMMENT         :   ('--' ~[\n\r]*) ->skip
;




