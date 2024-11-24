from functools import cache


def test_cache(x):
    # @cache is a decorator that caches the return value based on the input arguments
    # it's like dp[n], so don't pass unhashable arguments(array, list, dict and so on)
    @cache
    def fib(n):
        if n < 2:
            return n
        return fib(n - 1) + fib(n - 2)

    return fib(x)


print(test_cache(10))
