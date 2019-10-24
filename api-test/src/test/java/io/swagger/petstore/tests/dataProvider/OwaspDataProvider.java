package io.swagger.petstore.tests.dataProvider;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * @author Vitalii Smokov 26.03.2019
 */
public class OwaspDataProvider {

//  TODO
//  https://www.owasp.org/index.php/OWASP_Testing_Guide_Appendix_C:_Fuzz_Vectors#Format_String_Errors_.28FSE.29

    private static Stream<Arguments> getFormatStringErrors() {
        return Stream.of(
                Arguments.of("%s%p%x%d"),
                Arguments.of(".1024d"),
                Arguments.of("%.2049d"),
                Arguments.of("%p%p%p%p"),
                Arguments.of("%x%x%x%x"),
                Arguments.of("%d%d%d%d"),
                Arguments.of("%s%s%s%s"),
                Arguments.of("%99999999999s"),
                Arguments.of("%08x"),
                Arguments.of("%%20d"),
                Arguments.of("%%20n"),
                Arguments.of("%%20x"),
                Arguments.of("%%20s"),
                Arguments.of("%s%s%s%s%s%s%s%s%s%s"),
                Arguments.of("%p%p%p%p%p%p%p%p%p%p"),
                Arguments.of("%#0123456x%08x%x%s%p%d%n%o%u%c%h%l%q%j%z%Z%t%i%e%g%f%a%C%S%08x%%"),
                Arguments.of("%s x 129"),
                Arguments.of("%x x 257")
        );
    }

    private static Stream<Arguments> getIntOverflows() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(0x100),
                Arguments.of(0x1000),
                Arguments.of(0x3fffffff),
                Arguments.of(0x7ffffffe),
                Arguments.of(0x7fffffff),
                Arguments.of(0x80000000),
                Arguments.of(0xfffffffe),
                Arguments.of(0xffffffff),
                Arguments.of(0x10000),
                Arguments.of(0x100000)
        );
    }

    private static Stream<Arguments> getPassiveSQLInjection() {
        return Stream.of(
                Arguments.of("'||(elt(-3+5,bin(15),ord(10),hex(char(45))))"),
                Arguments.of("||6"),
                Arguments.of("'||'6"),
                Arguments.of("(||6)"),
                Arguments.of("' OR 1=1-- "),
                Arguments.of("OR 1=1"),
                Arguments.of("' OR '1'='1"),
                Arguments.of("; OR '1'='1'"),
                Arguments.of("%22+or+isnull%281%2F0%29+%2F*"),
                Arguments.of("%27+OR+%277659%27%3D%277659"),
                Arguments.of("%22+or+isnull%281%2F0%29+%2F*"),
                Arguments.of("%27+--+"),
                Arguments.of("' or 1=1--"),
                Arguments.of("\" or 1=1--"),
                Arguments.of("' or 1=1 /*"),
                Arguments.of("or 1=1--"),
                Arguments.of("' or 'a'='a"),
                Arguments.of("\" or \"a\"=\"a"),
                Arguments.of("') or ('a'='a"),
                Arguments.of("Admin' OR '"),
                Arguments.of("'%20SELECT%20*%20FROM%20INFORMATION_SCHEMA.TABLES--"),
                Arguments.of(") UNION SELECT%20*%20FROM%20INFORMATION_SCHEMA.TABLES;"),
                Arguments.of("' having 1=1--"),
                Arguments.of("' having 1=1--"),
                Arguments.of("' group by userid having 1=1--"),
                Arguments
                        .of("' SELECT name FROM syscolumns WHERE id = (SELECT id FROM sysobjects WHERE name = tablename')--"),
                Arguments.of("' or 1 in (select @@version)--"),
                Arguments.of("' union all select @@version--"),
                Arguments.of("' OR 'unusual' = 'unusual'"),
                Arguments.of("' OR 'something' = 'some'+'thing'"),
                Arguments.of("' OR 'text' = N'text'"),
                Arguments.of("' OR 'something' like 'some%'"),
                Arguments.of("' OR 2 > 1"),
                Arguments.of("' OR 'text' > 't'"),
                Arguments.of("' OR 'whatever' in ('whatever')"),
                Arguments.of("' OR 2 BETWEEN 1 and 3"),
                Arguments.of("' or username like char(37);"),
                Arguments.of("' union select * from users where login = char(114,111,111,116);"),
                Arguments.of("' union select "),
                Arguments.of("Password:*/=1--"),
                Arguments.of("UNI/**/ON SEL/**/ECT"),
                Arguments.of("'; EXECUTE IMMEDIATE 'SEL' || 'ECT US' || 'ER'"),
                Arguments.of("'; EXEC ('SEL' + 'ECT US' + 'ER')"),
                Arguments.of("'/**/OR/**/1/**/=/**/1"),
                Arguments.of("' or 1/*"),
                Arguments.of("+or+isnull%281%2F0%29+%2F*"),
                Arguments.of("%27+OR+%277659%27%3D%277659"),
                Arguments.of("%22+or+isnull%281%2F0%29+%2F*"),
                Arguments.of("%27+--+&password="),
                Arguments
                        .of("'; begin declare @var varchar(8000) set @var=':' select @var=@var+'+login+'/'+password+' ' from users where login > "),
                Arguments.of(" @var select @var as var into temp end --"),
                Arguments.of("' and 1 in (select var from temp)--"),
                Arguments.of("' union select 1,load_file('/etc/passwd'),1,1,1;"),
                Arguments.of("1;(load_file(char(47,101,116,99,47,112,97,115,115,119,100))),1,1,1;"),
                Arguments.of("' and 1=( if((load_file(char(110,46,101,120,116))<>char(39,39)),1,0));")
        );
    }

    private static Stream<Arguments> getActiveSQLInjection() {
        return Stream.of(
                Arguments.of("'; exec master..xp_cmdshell 'ping 10.10.1.2'--"),
                Arguments.of("CREATE USER name IDENTIFIED BY 'pass123'"),
                Arguments
                        .of("CREATE USER name IDENTIFIED BY pass123 TEMPORARY TABLESPACE temp DEFAULT TABLESPACE users; "),
                Arguments.of("' ; drop table temp --"),
                Arguments.of("exec sp_addlogin 'name' , 'password'"),
                Arguments.of("exec sp_addsrvrolemember 'name' , 'sysadmin'"),
                Arguments
                        .of("INSERT INTO mysql.user (user, host, password) VALUES ('name', 'localhost', PASSWORD('pass123'))"),
                Arguments.of("GRANT CONNECT TO name; GRANT RESOURCE TO name;"),
                Arguments
                        .of("INSERT INTO Users(Login, Password, Level) VALUES( char(0x70) + char(0x65) + char(0x74) + char(0x65) + char(0x72) + char(0x70) "),
                Arguments.of(" + char(0x65) + char(0x74) + char(0x65) + char(0x72),char(0x64)")

        );
    }


}
