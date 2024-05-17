import os
from datetime import *
from io import BytesIO
from traceback import print_exc

from flask import Flask, jsonify, request, send_file
from flask_cors import CORS
from jsonschema import ValidationError, validate
from pymongo import MongoClient, server_api

# from base64 import b64encode,b64decode

# from pymongo.server_api import ServerApi

# uri = "mongodb+srv://usernam:<password>@cluster0.kn4aekf.mongodb.net/?retryWrites=true&w=majority"

# # Create a new client and connect to the server
# client = MongoClient(uri, server_api=ServerApi('1'))

# # Send a ping to confirm a successful connection
# try:
# 	client.admin.command('ping')
# 	print("Pinged your deployment. You successfully connected to MongoDB!")
# except Exception as e:
# 	print(e)


app = Flask(__name__)
CORS(app)



@app.route('/')
def hello_world():
    print(request.get_json())

if __name__ == '__main__':
	try:
		# app.run(host='0.0.0.0', port=5000)
		app.run(
			host='0.0.0.0',
			port=5000,
			use_debugger=False,
			passthrough_errors=True,
			debug=True,
			use_reloader=True,
		)
	except KeyboardInterrupt:
		print("Server stopped with CTRL+C")
	except Exception as _:
		pass

