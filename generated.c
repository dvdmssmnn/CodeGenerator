#import "HookHelper.h"

#import "SQLiteStorage.h"
#import "ThreadStorage.h"
#import <vector>
#import <string>
#import <pthread.h>
#import <pthread.h>
#import "Config.h"
#import <CydiaSubstrate/CydiaSubstrate.h>

using namespace std;
CCCryptorStatus ____CCCryptorCreate(CCOperation op, CCAlgorithm alg, CCOptions options, const void * key, size_t keyLength, const void * iv, CCCryptorRef * cryptorRef);

CCCryptorStatus (*original_CCCryptorCreate)(CCOperation, CCAlgorithm, CCOptions, const void *, size_t, const void *, CCCryptorRef *);
__attribute__((constructor))
static void initialize() {
    MSHookFunction((void*)&CCCryptorCreate, (void*)&____CCCryptorCreate, (void**)&original_CCCryptorCreate);
}

CCCryptorStatus ____CCCryptorCreate(CCOperation op, CCAlgorithm alg, CCOptions options, const void * key, size_t keyLength, const void * iv, CCCryptorRef * cryptorRef)
{
    thread_storage_t *thread_infos = get_thread_infos();
    set_enabled(false);
    call_id_t call_id;
    pthread_mutex_lock(&counter_mutex);
    ++counter;
    call_id = counter;
    pthread_mutex_unlock(&counter_mutex);
    call_id_t caller_id = 0;
    if (thread_infos->call_id_stack->size()) {
        caller_id = thread_infos->call_id_stack->back();
    }
    thread_infos->call_id_stack->push_back(call_id);
    parameter_t *parameters = NULL;
    parameters = (parameter_t*)calloc(7, sizeof(parameter_t));
//Parse parameter op
    parameters[0].description = NULL;
    strncpy(parameters[0].type, "CCOperation", MAX_TYPE_LENGTH);
    snprintf(parameters[0].value, MAX_VALUE_LENGTH, "%d", (int)op);
//Parse parameter alg
    parameters[1].description = NULL;
    strncpy(parameters[1].type, "CCAlgorithm", MAX_TYPE_LENGTH);
    snprintf(parameters[1].value, MAX_VALUE_LENGTH, "%d", (int)alg);
//Parse parameter options
    parameters[2].description = NULL;
    strncpy(parameters[2].type, "CCOptions", MAX_TYPE_LENGTH);
    snprintf(parameters[2].value, MAX_VALUE_LENGTH, "%d", (int)options);
//Parse parameter key
    parameters[3].description = NULL;
    strncpy(parameters[3].type, "^void", MAX_TYPE_LENGTH);
    snprintf(parameters[3].value, MAX_VALUE_LENGTH, "0x%X", (register_t)key);
    parameters[3].description = (char*)calloc(2*keyLength+1, sizeof(char));
    NSData *key_data = [NSData dataWithBytes:key length:keyLength];
    strncpy(parameters[3].description, [[[[[key_data description] stringByReplacingOccurrencesOfString:@"<" withString:@""] stringByReplacingOccurrencesOfString:@" " withString:@""] stringByReplacingOccurrencesOfString:@">" withString:@""] UTF8String], 2*keyLength);
//Parse parameter keyLength
    parameters[4].description = NULL;
    strncpy(parameters[4].type, "size_t", MAX_TYPE_LENGTH);
    snprintf(parameters[4].value, MAX_VALUE_LENGTH, "%d", (int)keyLength);
//Parse parameter iv
    parameters[5].description = NULL;
    strncpy(parameters[5].type, "^void", MAX_TYPE_LENGTH);
    snprintf(parameters[5].value, MAX_VALUE_LENGTH, "0x%X", (register_t)iv);
    if(iv&&alg==kCCAlgorithmAES) {
        parameters[5].description = (char*)calloc(2*16+1, sizeof(char));
        NSData *iv_data = [NSData dataWithBytes:iv length:16];
        strncpy(parameters[5].description, [[[[[iv_data description] stringByReplacingOccurrencesOfString:@"<" withString:@""] stringByReplacingOccurrencesOfString:@" " withString:@""] stringByReplacingOccurrencesOfString:@">" withString:@""] UTF8String], 2*16);

    }
    if(iv&&alg!=kCCAlgorithmAES) {
        parameters[5].description = (char*)calloc(2*8+1, sizeof(char));
        NSData *iv_data = [NSData dataWithBytes:iv length:8];
        strncpy(parameters[5].description, [[[[[iv_data description] stringByReplacingOccurrencesOfString:@"<" withString:@""] stringByReplacingOccurrencesOfString:@" " withString:@""] stringByReplacingOccurrencesOfString:@">" withString:@""] UTF8String], 2*8);

    }
    set_enabled(true);
    CCCryptorStatus return_value = original_CCCryptorCreate(op, alg, options, key, keyLength, iv, cryptorRef);
    set_enabled(false);
//Parse parameter cryptorRef
    parameters[6].description = NULL;
    strncpy(parameters[6].type, "^CCCryptorRef", MAX_TYPE_LENGTH);
    snprintf(parameters[6].value, MAX_VALUE_LENGTH, "0x%X", (int)*cryptorRef);
    if (enabled_) {
        dispatch_async(db_queue, ^ {
            insert_call(call_id, caller_id, "CommonCrypto", "CCCryptorCreate", "0x0", parameters, 7);
            free(parameters);
        });
    } else {
        free(parameters);
    }
    parameter_t return_param;
    return_param.description = NULL;
    strncpy(return_param.type, "CCCryptorStatus", MAX_TYPE_LENGTH);
    snprintf(return_param.value, MAX_VALUE_LENGTH, "%d", (int)return_value);
    if (enabled_) {
        dispatch_async(db_queue, ^ {
            insert_return(call_id, &return_param);
        });
    }
    thread_infos->call_id_stack->pop_back();
    set_enabled(true);
    return return_value;
}


