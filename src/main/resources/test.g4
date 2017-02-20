grammar test;

scenario: asSales asTrader
          sendRfq
          workflow
          ;

asSales: AS_SALES salesName;
salesName: STRING;

asTrader: AS_TRADER traderName;
traderName: STRING;

workflow    : waitStatus EOF
            | waitStatus waitStatus EOF
            | waitStatus waitStatus sendPrice EOF
            | waitStatus waitStatus sendPrice waitStatus EOF
            | waitStatus waitStatus sendPrice waitStatus sendOrderAccepted EOF
            | waitStatus waitStatus sendPrice waitStatus sendOrderAccepted waitStatus EOF
            ;

waitStatus: 'then wait status' statusName timeoutInSecond?;
statusName: STRING;
timeoutInSecond: NUMBER;

sendRfq: 'when the sales send a rfq' (json|smartrfq);

sendPrice: 'send price' json;

verifyRfq: uuid verifyStatement;

uuid: STRING;

verifyStatement: STRING STRING;

sendOrderAccepted: 'send order accepted' json;


smartrfq: STRING;

json:   object
    |   array
    ;

object
    :   '{' pair (',' pair)* '}'
    |   '{' '}' // empty object
    ;
pair:   STRING ':' value ;

array
    :   '[' value (',' value)* ']'
    |   '[' ']' // empty array
    ;

value
    :   STRING
    |   NUMBER
    |   INT
    |   object  // recursion
    |   array   // recursion
    |   'true'  // keywords
    |   'false'
    |   'null'
    ;

//AS_SALES: 'as sales ';
AS_SALES: 'given a sales ';
AS_TRADER: 'given a trader ';

STRING :  '"' (ESC | ~["\\])* '"' ;

fragment ESC :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;

NUMBER
    :   '-'? INT '.' [0-9]+ EXP? // 1.35, 1.35E-9, 0.3, -4.5
    |   '-'? INT EXP             // 1e10 -3e4
    |   '-'? INT                 // -3, 45
    ;

fragment INT :   '0' | [1-9] [0-9]* ; // no leading zeros

fragment EXP :   [Ee] [+\-]? INT ; // \- since - means "range" inside [...]

LINE_COMMENT : ('//'|'#') .*? '\r'? '\n' -> skip ;

WS  :   [ \t\n\r]+ -> skip ;