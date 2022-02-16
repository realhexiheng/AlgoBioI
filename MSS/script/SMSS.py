#!/usr/bin/python3
import os
import cgi, cgitb, jinja2, subprocess
import urllib.request
import urllib.error

cgitb.enable()
# print content type
print("Content-type:text/html\r\n\r\n")

form = cgi.FieldStorage()

UPLOAD_DIR='./uploads/'

protein_id = None

seq_file_path = None

smss_type = None

input_file_name = None

download_file_name = None

out = ''

warnings = ''

def protein_download(id):
    try:
        req = urllib.request.Request('https://www.uniprot.org/uniprot/' + id + '.' + 'fasta')
        with urllib.request.urlopen(req) as file:
            resp = file.read().decode('UTF-8')
            return resp
    except urllib.error.HTTPError:
        warnings = 'no pdb found for id: ' + id

if "protein_id" in form:
    protein_id = form.getvalue('protein_id')
    if protein_id != "":
        download_file_name = UPLOAD_DIR + protein_id + ".fasta"
        temp = protein_download(protein_id)
        with open(UPLOAD_DIR + protein_id + ".fasta", 'w', encoding='utf-8') as f:
            for line in temp:
                f.write(line)
if form.getvalue('user_defined_seq'):
    user_seq = form.getvalue('user_defined_seq')
    if user_seq is not None and user_seq != "":
        input_file_name = UPLOAD_DIR + "user_defined_seq.fasta"
        with open(input_file_name, 'w') as fout:
            # file has to be read in chunks because of bytes -> wb
            fout.write(user_seq)
if "sequence" in form:
    fileItem = form['sequence']
    # file is now a python object
    if fileItem.filename:
        seq_file_path = UPLOAD_DIR + os.path.join(os.path.basename(fileItem.filename))
        with open(seq_file_path, 'wb') as fout:
            # file has to be read in chunks because of bytes -> wb
            while True:
                chunk = fileItem.file.read(100000)
                if not chunk:
                    break
                # write the file content as a file
                fout.write(chunk)

if "mode" in form:
    mode = str(form["mode"].value)

if download_file_name: 
    # using Swissprot id
        out =  subprocess.check_output(["java","-jar","/home/h/hexih/public_html/MSS.jar", "-i", download_file_name, mode ,"-mode", "aS"])   
        out = out.decode('utf-8')
elif input_file_name is not None:
    # using user self-defined sequence
        out =  subprocess.check_output(["java","-jar","/home/h/hexih/public_html/MSS.jar", "-i", input_file_name, mode ,"-mode", "aS"])
        out = out.decode('utf-8')
elif seq_file_path is not None:
    # using user uploaded sequence
        out =  subprocess.check_output(["java","-jar","/home/h/hexih/public_html/MSS.jar", "-i", seq_file_path, mode ,"-mode", "aS"])
        out = out.decode('utf-8')

if out != None:
    txt_path = "./downloads/smss_result.txt"
    with open(txt_path, 'w') as txt_file:
        txt_file.write(out)

temp = ""

for line in out:
    line = line.replace("\n", "<br>")
    line = line.replace("\t", "&emsp;") 
    temp += line
out = temp
# delete file after it was passed to Calculatefile

#if out != None:
# os.remove(uploaded_file_path)

env = jinja2.Environment(loader=jinja2.FileSystemLoader('./templates'))
template = env.get_template('SMSS_predict.html')

print(template.render(
    output=out + "\n" + warnings
))

