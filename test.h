
CCCryptorStatus CCCryptorCreate(
    CCOperation op,
    CCAlgorithm alg,
    CCOptions options,
    const void *key,
    size_t keyLength,
    const void *iv,
    CCCryptorRef *cryptorRef)
{
	data(key, keyLength);
  if (iv && alg == kCCAlgorithmAES) data(iv, 16);
  if (iv && alg != kCCAlgorithmAES) data(iv, 8);
  out(cryptorRef);
}