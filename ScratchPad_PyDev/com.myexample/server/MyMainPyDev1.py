import socket
import urllib.request

URL = "http://www.google.co.in"
HOST = ""
PORT = 8888

def readFromURL():
    r = urllib.request.urlopen(URL)
    print(r.read())

# Try telnet to localhost and port mentioned above
def serverServerSocket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((HOST, PORT))
    s.listen()
    print('Socket now listening')
    (conn, addr) = s.accept()
    print(conn, addr)
    print('Connected with ' + addr[0] + ':' + str(addr[1]))
    PROMPT = "\n\nPrompt > "
    conn.send(str.encode(PROMPT))
    while True:
        data = conn.recv(1024)
        d = data.decode("utf-8").strip()
        if d == "exit":
            conn.send(str.encode("Exiting the remote connection...\n"))
            break
        elif d == "ls":
            result = "list of files requested..." + PROMPT
        else:
            result = "Invalid request." + PROMPT
        conn.send(str.encode(result))
    s.close()
    
#readFromURL()
serverServerSocket()
