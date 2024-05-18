import csv

# CSV 파일 경로
csv_file_path = r'C:\Users\symel\Downloads\megaGymDataset.csv'

# CSV 파일 읽기
with open(csv_file_path, newline='', encoding='utf-8') as csvfile:
    csv_reader = csv.reader(csvfile)
    
    # CSV 헤더 읽기
    header = next(csv_reader)
    
    # 데이터 읽기
    data = [row for row in csv_reader]

# 테이블 이름
table_name = 'exercise'

# SQL `INSERT` 쿼리 생성
insert_queries = []

for row in data:
    # 이스케이프 처리
    escaped_values = [value.replace("'", "''") for value in row]
    # 삼중 인용부호로 감싸기
    values = ', '.join([f"'''{value}'''" for value in escaped_values])
    query = f"INSERT INTO {table_name} ({', '.join(header)}) VALUES ({values});"
    insert_queries.append(query)

# 텍스트 파일 경로
output_file_path = 'insert_queries3.txt'

# 텍스트 파일에 쿼리 저장
with open(output_file_path, 'w', encoding='utf-8') as file:
    for query in insert_queries:
        file.write(query + '\n')

print(f'Queries have been written to {output_file_path}')
