import pandas as pd
import re

def parse_markdown_tasks(markdown_content):
    tasks = []
    current_phase = None
    current_phase_task_index = -1
    lines = markdown_content.splitlines() # Correct way to split lines

    # States for parsing sections under a phase
    parsing_objetivo = False
    parsing_entregaveis = False
    parsing_recursos = False
    parsing_duracao = False
    parsing_criterios = False

    for line in lines:
        line = line.strip()
        if not line: # Skip empty lines
            continue

        phase_match = re.match(r"^\*\*(Fase \d+: .*)\*\*", line)
        if phase_match:
            current_phase = phase_match.group(1)
            tasks.append({
                "Categoria/Tópico": current_phase,
                "Subtarefa": "Planeamento e Gestão da Fase",
                "Descrição": f"Coordenação geral e acompanhamento da {current_phase}",
                "Prioridade": "",
                "Responsável": "",
                "Estado": "Pendente",
                "Data de Início": "",
                "Prazo": ""
            })
            current_phase_task_index = len(tasks) - 1
            # Reset states for the new phase
            parsing_objetivo = False
            parsing_entregaveis = False
            parsing_recursos = False
            parsing_duracao = False
            parsing_criterios = False
            continue

        if not current_phase:
            continue

        # Section Headers
        if re.match(r"^\*   \*\*Objetivo:\*\*", line):
            parsing_objetivo = True
            parsing_entregaveis = False
            parsing_recursos = False
            parsing_duracao = False
            parsing_criterios = False
            objetivo_text = re.sub(r"^\*   \*\*Objetivo:\*\*", "", line).strip()
            if objetivo_text: # If objective text is on the same line
                 tasks.append({
                    "Categoria/Tópico": current_phase,
                    "Subtarefa": "Objetivo da Fase",
                    "Descrição": objetivo_text,
                    "Prioridade": "", "Responsável": "", "Estado": "Pendente", "Data de Início": "", "Prazo": ""
                })
            continue
        elif re.match(r"^\*   \*\*Entregáveis Esperados:\*\*", line):
            parsing_objetivo = False
            parsing_entregaveis = True
            parsing_recursos = False
            parsing_duracao = False
            parsing_criterios = False
            continue
        elif re.match(r"^\*   \*\*Recursos/Perfis Envolvidos:\*\*", line):
            parsing_objetivo = False
            parsing_entregaveis = False
            parsing_recursos = True
            parsing_duracao = False
            parsing_criterios = False
            recursos_text = re.sub(r"^\*   \*\*Recursos/Perfis Envolvidos:\*\*", "", line).strip()
            if recursos_text and current_phase_task_index != -1:
                tasks[current_phase_task_index]["Responsável"] = recursos_text
            continue
        elif re.match(r"^\*   \*\*Duração Estimada:\*\*", line):
            parsing_objetivo = False
            parsing_entregaveis = False
            parsing_recursos = False
            parsing_duracao = True
            parsing_criterios = False
            duracao_text = re.sub(r"^\*   \*\*Duração Estimada:\*\*", "", line).strip()
            if duracao_text and current_phase_task_index != -1:
                 tasks[current_phase_task_index]["Prazo"] = duracao_text
            continue
        elif re.match(r"^\*   \*\*Critérios de Aceitação:\*\*", line):
            parsing_objetivo = False
            parsing_entregaveis = False
            parsing_recursos = False
            parsing_duracao = False
            parsing_criterios = True
            continue

        # Data items under sections
        item_match = None
        if parsing_objetivo and not re.match(r"^\*   \*\*", line): # if it's not another section header
            item_match = line # The whole line is the description of the objective if it's multiline
            if item_match:
                 # Append to existing objective description or create new if first line after header
                # For simplicity, let's assume objective is single line or handled by the header line itself
                # If objective can be multi-line, this needs more complex logic
                pass # Handled by the header for now

        elif parsing_entregaveis:
            # Matches lines like: *   - Documento de arquitetura atualizado (baseado nesta análise inicial).
            # Or: *   *   Documento de arquitetura atualizado...
            sub_item_match = re.match(r"^\*   -\s*(.*)", line)
            if not sub_item_match:
                sub_item_match = re.match(r"^\*   \*(.*)\*", line) # For items like * *Item* *
                if sub_item_match:
                     item_text = sub_item_match.group(1).strip()
                     # Check if there's a description part like: * *Item*: Description
                     desc_match = re.match(r"(.*?):\s*(.*)", item_text)
                     if desc_match:
                         subtarefa = "Entregável: " + desc_match.group(1).strip()
                         descricao = desc_match.group(2).strip()
                     else:
                         subtarefa = "Entregável: " + item_text
                         descricao = ""
                     tasks.append({
                        "Categoria/Tópico": current_phase, "Subtarefa": subtarefa,
                        "Descrição": descricao, "Prioridade": "", "Responsável": "",
                        "Estado": "Pendente", "Data de Início": "", "Prazo": ""
                    })
                # else, it might be a plain text line continuing a description, ignore for now
            else: # Matched "* - Item"
                item_text = sub_item_match.group(1).strip()
                tasks.append({
                    "Categoria/Tópico": current_phase, "Subtarefa": "Entregável: " + item_text,
                    "Descrição": "", "Prioridade": "", "Responsável": "",
                    "Estado": "Pendente", "Data de Início": "", "Prazo": ""
                })

        elif parsing_recursos:
            # Text under "Recursos/Perfis Envolvidos" is assigned to the main phase task
            if line and current_phase_task_index != -1:
                if tasks[current_phase_task_index]["Responsável"]: # Append if already started
                    tasks[current_phase_task_index]["Responsável"] += "; " + line
                else:
                    tasks[current_phase_task_index]["Responsável"] = line

        elif parsing_duracao:
             # Text under "Duração Estimada" is assigned to the main phase task
            if line and current_phase_task_index != -1:
                if tasks[current_phase_task_index]["Prazo"]: # Append if already started
                     tasks[current_phase_task_index]["Prazo"] += "; " + line
                else:
                    tasks[current_phase_task_index]["Prazo"] = line

        elif parsing_criterios:
            # Matches lines like: *   - Documentação validada pelas partes interessadas (stakeholders).
            sub_item_match = re.match(r"^\*   -\s*(.*)", line)
            if sub_item_match:
                item_text = sub_item_match.group(1).strip()
                tasks.append({
                    "Categoria/Tópico": current_phase, "Subtarefa": "Critério de Aceitação: " + item_text,
                    "Descrição": "", "Prioridade": "", "Responsável": "",
                    "Estado": "Pendente", "Data de Início": "", "Prazo": ""
                })
            # else, it might be a plain text line continuing a description, ignore for now

    return tasks

def main():
    # Read the Markdown file content
    try:
        with open("DOCUMENTACAO_PROJETO.md", "r", encoding="utf-8") as f:
            markdown_content = f.read()
    except FileNotFoundError:
        print("Erro: O ficheiro DOCUMENTACAO_PROJETO.md não foi encontrado.")
        return

    # Focus on "Parte 3 – Plano de Execução do Projeto"
    # Make the regex non-greedy for the section title to avoid consuming subsequent similar titles if any
    plan_execution_section = re.search(r"## Parte 3 – Plano de Execução do Projeto(.*?)(?=## Parte \d+ –|$)", markdown_content, re.DOTALL | re.IGNORECASE)

    if not plan_execution_section:
        print("Erro: Secção 'Parte 3 – Plano de Execução do Projeto' não encontrada no ficheiro.")
        return

    relevant_content = plan_execution_section.group(1)

    parsed_tasks = parse_markdown_tasks(relevant_content)

    if not parsed_tasks:
        print("Nenhuma tarefa foi extraída. Verifique o formato do Markdown ou a lógica de parsing.")
        return

    df = pd.DataFrame(parsed_tasks)

    # Reorder columns as requested
    columns_ordered = [
        "Categoria/Tópico",
        "Subtarefa",
        "Descrição",
        "Prioridade",
        "Responsável",
        "Estado",
        "Data de Início",
        "Prazo"
    ]
    df = df[columns_ordered]

    # Export to Excel
    try:
        excel_filename = "plano_de_projeto.xlsx"
        df.to_excel(excel_filename, index=False)
        print(f"Ficheiro Excel '{excel_filename}' criado com sucesso.")
    except Exception as e:
        print(f"Erro ao exportar para Excel: {e}")

if __name__ == "__main__":
    main()
