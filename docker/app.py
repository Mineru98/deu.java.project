import os
import cv2
import pytesseract
from PIL import Image
from werkzeug import secure_filename
from flask import Flask, jsonify, request

__author__ = 'Rick Torzynski <ricktorzynski@gmail.com>'
__source__ = ''

app = Flask(__name__)
UPLOAD_FOLDER = './static/uploads'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER 
app.config['MAX_CONTENT_LENGTH'] = 10 * 1024 * 1024

@app.route('/uploader', methods = ['POST'])
def upload_file():
  try:
    if request.method == 'POST':
      f = request.files['file']

      # create a secure filename
      filename = secure_filename(f.filename)

      # save file to /static/uploads
      filepath = os.path.join(app.config['UPLOAD_FOLDER'],filename)
      f.save(filepath)

      # load the example image and convert it to grayscale
      image = cv2.imread(filepath)
      gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

      # apply thresholding to preprocess the image
      gray = cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)[1]

      # apply median blurring to remove any blurring
      gray = cv2.medianBlur(gray, 3)

      # save the processed image in the /static/uploads directory
      ofilename = os.path.join(app.config['UPLOAD_FOLDER'],"{}.png".format(os.getpid()))
      cv2.imwrite(ofilename, gray)

      # perform OCR on the processed image
      text = pytesseract.image_to_string(Image.open(ofilename), lang="kor")

      # remove the processed image
      os.remove(ofilename)
      data = { "code": 200, 'text' : text }
      return jsonify(data)
  except Exception as e:
    data = { "code": 400, 'text' : str(e) }
    return jsonify(data)

if __name__ == '__main__':
  port = int(os.environ.get('PORT', 5000))
  app.run(debug=True, host='0.0.0.0', port=port)