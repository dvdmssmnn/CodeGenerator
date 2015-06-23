#CodeGenerator
This is a simple code generator that helps creating function hooks using CydiaSubstrate (http://www.cydiasubstrate.com/).

##Features

The generator mainly parses function headers and generates a hook function that parses raw values of the parameters and, if available, the return value.

What is not done is anything like type checking, syntax checking etc... Plain C/C++ function headers should work, but no macros or anything like that. If you use a type that needs to be imported add an *#import* or *#include* statement at the top.

###Parse parameter
Per default the hook retrieves only the raw value of all parameters, but you can tell the generator to retrieve a description of the parameter using some functions:
* base64(name, length)
* cstring(name)
* data(name, length)
* utf8(name, length)

You can also define to retrieve the description after the orginal function was called by putting the description function call into an *out(description(...))* call.

And it is also possible to make the parsing conditional:

    if (condition) action

####Example
    void some_function(void *a, size_t len_a, void *b, size_t len_b)
    {
      data(a, len_a);
      if (len_b > 0) out(data(b, len_b));
    }
*a* gets parsed before the original function call and *b* gets parsed after the original function call **if** *len_b* was greater than 0.

###Private symbols

If a function is not defined in a public header you can tell the generator to try to find the symbol using the CydiaSubstrate framework by appending an questionmark after the function header.

Example:

    void foo()?;
