#! /usr/bin/env python
# -*- coding: utf-8 -*-
#@+leo-ver=5-thin
#@+node:swot.20200205164205.1: * @file key_mouse.py
#@@first
#@@first
#@@language python
#@@tabwidth -4
#@+others
#@+node:swot.20200205164205.2: ** var
from pynput import keyboard, mouse

key_count = 128866
mouse_count = 12344
total_count = 141210
#@+node:swot.20200205164205.3: ** mouse


def on_move(x, y):
    pass
    '''
    print('Pointer moved to {0}'.format(
        (x, y)))
    '''


def on_click(x, y, button, pressed):

    global mouse_count, total_count
    if not pressed:
        mouse_count += 1
        total_count += 1
        print('total: {1} mouse: {0}'.format(mouse_count, total_count))

    '''
    print('{0} at {1}'.format(
        'Pressed' if pressed else 'Released',
        (x, y)))
    '''
    '''
    if not pressed:
        # Stop listener
        return False
    '''


def on_scroll(x, y, dx, dy):
    pass
    '''
    print('Scrolled {0} at {1}'.format(
        'down' if dy < 0 else 'up',
        (x, y)))
    '''
#@+node:swot.20200205164205.4: ** keyboard
# def on_press(key):
def on_release(key):

    # print(key)
    global key_count, total_count
    key_count += 1
    total_count += 1

    try:
        print('total: {2} key: {0} alphanumeric key {1} pressed'.format(
            key_count, key.char, total_count))
    except AttributeError:
        print('total: {2} key: {0} special key {1} pressed'.format(
            key_count, key, total_count))


#@+node:swot.20200205164205.5: ** listener
"""
# Collect events until released
with mouse.Listener(
        on_move=on_move,
        on_click=on_click,
        on_scroll=on_scroll) as mouse_listener:
    mouse_listener.join()


# ...or, in a non-blocking fashion:
listener = mouse.Listener(
    on_move=on_move,
    on_click=on_click,
    on_scroll=on_scroll)
listener.start()


# Collect events until released
with keyboard.Listener(
        on_press=on_press,
        on_release=on_release) as listener:
    listener.join()


# ...or, in a non-blocking fashion:
listener = keyboard.Listener(
    on_press=on_press,
    on_release=on_release)
listener.start()
"""

# with keyboard.Listener(on_press=on_press) as mouse_listener:
#     with mouse.Listener(on_click=on_click) as keyboard_listener:
#         keyboard_listener.join()
#         mouse_listener.join()
# with keyboard.Listener(on_press=on_press) as keyboard_listener:
with keyboard.Listener(on_release=on_release) as keyboard_listener:
    with mouse.Listener(on_click=on_click) as mouse_listener:
        keyboard_listener.join()
        mouse_listener.join()
#@+node:swot.20200205164205.6: ** if False
if False:
    pass
    #@+others
    #@-others
#@-others
#@-leo
