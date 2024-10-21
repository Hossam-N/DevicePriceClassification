from flask import Flask, request, jsonify
from joblib import load  
import numpy as np
import pandas as pd


app = Flask(__name__)

model = load('./Outputs/svm_model.pkl')
scaler = load('./Outputs/scaler.pkl')
features = load('./Outputs/selected_features.pkl')

def convert(camelcase_key):
    return ''.join(['_' + i.lower() if i.isupper() else i for i in camelcase_key]).lstrip('_')



@app.route('/api/predict', methods=['POST'])
def predict():
    device_specs = request.get_json()
    device_specs_dict = {convert(k): v for k,v in device_specs.items() if k not in {'priceRange','id'}}
    # convert the dictionary to a data frame
    device_specs_df = pd.DataFrame([device_specs_dict])

    device_specs_df.iloc[:,:] = scaler.transform(device_specs_df)


    device_specs_df= device_specs_df[features]
    print(device_specs_df.head())

    prediction = model.predict(device_specs_df)

    return jsonify(int(prediction[0]))

if __name__ == '__main__':
    app.run(host='127.0.0.1',port=5000, debug=True)










    

    


    
