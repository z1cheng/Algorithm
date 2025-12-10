import asyncio
import random


async def consumer(queue: asyncio.Queue, id):
    while True:
        try:
            val = await asyncio.wait_for(queue.get(), timeout=10)
            print("{} get a val: {}".format(id, val))
            await asyncio.sleep(1)
        except asyncio.TimeoutError:
            print("{} timeout, exiting".format(id))
            break


async def producer(queue: asyncio.Queue, id):
    for i in range(5):
        val = random.randint(1, 10)
        await queue.put(val)
        print("{} put a val: {}".format(id, val))
        await asyncio.sleep(1)


async def main():
    queue = asyncio.Queue()
    consumer_1 = asyncio.create_task(consumer(queue, "consumer_1"))
    consumer_2 = asyncio.create_task(consumer(queue, "consumer_2"))

    producer_1 = asyncio.create_task(producer(queue, "producer_1"))
    producer_2 = asyncio.create_task(producer(queue, "producer_2"))

    await asyncio.gather(
        consumer_1, consumer_2, producer_1, producer_2, return_exceptions=True
    )


asyncio.run(main())
