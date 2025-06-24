import pandas as pd
import re

def parse_plan_execution_tasks(markdown_content):
    tasks = []
    current_phase = None
    current_phase_task_index = -1
    lines = markdown_content.splitlines()

    parsing_objetivo = False
    parsing_entregaveis = False
    parsing_recursos = False
    parsing_duracao = False
    parsing_criterios = False

    for line in lines:
        line = line.strip()
        if not line:
            continue

        phase_match = re.match(r"^\*\*(Fase \d+: .*)\*\*", line)
        if phase_match:
            current_phase = phase_match.group(1)
            tasks.append({
                "Categoria/Tópico": current_phase,
                "Subtarefa": "Planeamento e Gestão da Fase",
                "Descrição": f"Coordenação geral e acompanhamento da {current_phase}",
                "Prioridade": "", "Responsável": "", "Estado": "Pendente",
                "Data de Início": "", "Prazo": ""
            })
            current_phase_task_index = len(tasks) - 1
            parsing_objetivo = parsing_entregaveis = parsing_recursos = parsing_duracao = parsing_criterios = False
            continue

        if not current_phase:
            continue

        if re.match(r"^\*   \*\*Objetivo:\*\*", line):
            parsing_objetivo = True; parsing_entregaveis = parsing_recursos = parsing_duracao = parsing_criterios = False
            objetivo_text = re.sub(r"^\*   \*\*Objetivo:\*\*", "", line).strip()
            if objetivo_text:
                 tasks.append({
                    "Categoria/Tópico": current_phase, "Subtarefa": "Objetivo da Fase",
                    "Descrição": objetivo_text, "Prioridade": "", "Responsável": "",
                    "Estado": "Pendente", "Data de Início": "", "Prazo": ""
                })
            continue
        elif re.match(r"^\*   \*\*Entregáveis Esperados:\*\*", line):
            parsing_entregaveis = True; parsing_objetivo = parsing_recursos = parsing_duracao = parsing_criterios = False
            continue
        elif re.match(r"^\*   \*\*Recursos/Perfis Envolvidos:\*\*", line):
            parsing_recursos = True; parsing_objetivo = parsing_entregaveis = parsing_duracao = parsing_criterios = False
            recursos_text = re.sub(r"^\*   \*\*Recursos/Perfis Envolvidos:\*\*", "", line).strip()
            if recursos_text and current_phase_task_index != -1:
                tasks[current_phase_task_index]["Responsável"] = recursos_text
            continue
        elif re.match(r"^\*   \*\*Duração Estimada:\*\*", line):
            parsing_duracao = True; parsing_objetivo = parsing_entregaveis = parsing_recursos = parsing_criterios = False
            duracao_text = re.sub(r"^\*   \*\*Duração Estimada:\*\*", "", line).strip()
            if duracao_text and current_phase_task_index != -1:
                 tasks[current_phase_task_index]["Prazo"] = duracao_text
            continue
        elif re.match(r"^\*   \*\*Critérios de Aceitação:\*\*", line):
            parsing_criterios = True; parsing_objetivo = parsing_entregaveis = parsing_recursos = parsing_duracao = False
            continue

        if parsing_objetivo and not re.match(r"^\*   \*\*", line):
            # Assuming objective is single line or handled by the header line itself
            pass

        elif parsing_entregaveis:
            sub_item_match = re.match(r"^\*   -\s*(.*)", line) or re.match(r"^\*   \*(.*)\*", line)
            if sub_item_match:
                item_text = sub_item_match.group(1).strip()
                desc_match = re.match(r"(.*?):\s*(.*)", item_text)
                subtarefa, descricao = ("Entregável: " + desc_match.group(1).strip(), desc_match.group(2).strip()) if desc_match else ("Entregável: " + item_text, "")
                tasks.append({
                    "Categoria/Tópico": current_phase, "Subtarefa": subtarefa, "Descrição": descricao,
                    "Prioridade": "", "Responsável": "", "Estado": "Pendente", "Data de Início": "", "Prazo": ""
                })
        elif parsing_recursos:
            if line and current_phase_task_index != -1:
                tasks[current_phase_task_index]["Responsável"] += ("; " + line) if tasks[current_phase_task_index]["Responsável"] else line
        elif parsing_duracao:
            if line and current_phase_task_index != -1:
                tasks[current_phase_task_index]["Prazo"] += ("; " + line) if tasks[current_phase_task_index]["Prazo"] else line
        elif parsing_criterios:
            sub_item_match = re.match(r"^\*   -\s*(.*)", line)
            if sub_item_match:
                item_text = sub_item_match.group(1).strip()
                tasks.append({
                    "Categoria/Tópico": current_phase, "Subtarefa": "Critério de Aceitação: " + item_text,
                    "Descrição": "", "Prioridade": "", "Responsável": "", "Estado": "Pendente",
                    "Data de Início": "", "Prazo": ""
                })
    return tasks

def parse_user_stories(markdown_content):
    user_stories = []
    lines = markdown_content.splitlines()
    current_cu_title = None
    current_cu_ator = None
    current_cu_descricao_parts = []
    current_cu_fluxo_parts = []

    # Regex to capture CU title and its main description
    cu_title_desc_regex = r"^\*\*(CU\d+:\s*.*?)\*\*"
    # Regex to capture Ator
    ator_regex = r"^\*   \*\*Ator(es)?:\*\*(.*)"
    # Regex for the main description line of the CU (sometimes distinct from title)
    descricao_header_regex = r"^\*   \*\*Descrição:\*\*(.*)"
    # Regex for Fluxo Principal header
    fluxo_header_regex = r"^\*   \*\*Fluxo Principal:\*\*"
    # Regex for items within Fluxo Principal (numbered list)
    fluxo_item_regex = r"^\s*\d+\.\s+(.*)"


    parsing_descricao = False
    parsing_fluxo = False

    for line in lines:
        line = line.strip()
        if not line:
            continue

        # New CU found
        cu_title_match = re.match(cu_title_desc_regex, line)
        if cu_title_match:
            # If there was a previous CU, process it
            if current_cu_title and current_cu_ator:
                story_text = f"Como um {current_cu_ator}, eu quero {current_cu_title.split(':', 1)[1].strip()} para que [Benefício a ser definido/refinado]."
                descricao_completa = " ".join(current_cu_descricao_parts).strip()
                if current_cu_fluxo_parts:
                    descricao_completa += "\nFluxo Principal:\n" + "\n".join([f"- {item}" for item in current_cu_fluxo_parts])

                user_stories.append({
                    "Categoria/Tópico": "User Story", "Subtarefa": story_text,
                    "Descrição": descricao_completa, "Prioridade": "", "Responsável": "Equipa de Desenvolvimento",
                    "Estado": "A Fazer", "Data de Início": "", "Prazo": ""
                })

            # Reset for new CU
            current_cu_title = cu_title_match.group(1)
            current_cu_ator = None # Reset ator for new CU
            current_cu_descricao_parts = []
            current_cu_fluxo_parts = []
            parsing_descricao = False
            parsing_fluxo = False
            continue

        if not current_cu_title: # Only process if we are inside a CU block
            continue

        ator_match = re.match(ator_regex, line)
        if ator_match:
            current_cu_ator = ator_match.group(2).strip()
            parsing_descricao = False # Stop parsing general description if Ator is found
            parsing_fluxo = False
            continue

        descricao_header_match = re.match(descricao_header_regex, line)
        if descricao_header_match:
            parsing_descricao = True
            parsing_fluxo = False
            desc_text = descricao_header_match.group(1).strip()
            if desc_text: # Description text on the same line as header
                current_cu_descricao_parts.append(desc_text)
            continue

        fluxo_header_match = re.match(fluxo_header_regex, line)
        if fluxo_header_match:
            parsing_fluxo = True
            parsing_descricao = False # Stop general description parsing
            continue

        if parsing_descricao:
            # If it's not another section header, append to description
            if not (re.match(ator_regex, line) or re.match(fluxo_header_regex, line)):
                 current_cu_descricao_parts.append(line)

        elif parsing_fluxo:
            fluxo_item_match = re.match(fluxo_item_regex, line)
            if fluxo_item_match:
                current_cu_fluxo_parts.append(fluxo_item_match.group(1).strip())
            # If line doesn't match fluxo_item_regex but we are in fluxo, it might be a continuation or poorly formatted.
            # For now, only capture numbered items.

    # Add the last parsed CU if any
    if current_cu_title and current_cu_ator:
        story_text = f"Como um {current_cu_ator}, eu quero {current_cu_title.split(':', 1)[1].strip()} para que [Benefício a ser definido/refinado]."
        descricao_completa = " ".join(current_cu_descricao_parts).strip()
        if current_cu_fluxo_parts:
            descricao_completa += "\nFluxo Principal:\n" + "\n".join([f"- {item}" for item in current_cu_fluxo_parts])
        user_stories.append({
            "Categoria/Tópico": "User Story", "Subtarefa": story_text,
            "Descrição": descricao_completa, "Prioridade": "", "Responsável": "Equipa de Desenvolvimento",
            "Estado": "A Fazer", "Data de Início": "", "Prazo": ""
        })

    return user_stories


def main():
    try:
        with open("DOCUMENTACAO_PROJETO.md", "r", encoding="utf-8") as f:
            markdown_content = f.read()
    except FileNotFoundError:
        print("Erro: O ficheiro DOCUMENTACAO_PROJETO.md não foi encontrado.")
        return

    # Parse Plan Execution Tasks from Part 3
    plan_execution_section = re.search(r"## Parte 3 – Plano de Execução do Projeto(.*?)(?=## Parte \d+ –|$)", markdown_content, re.DOTALL | re.IGNORECASE)
    parsed_tasks_plan = []
    if plan_execution_section:
        relevant_content_plan = plan_execution_section.group(1)
        parsed_tasks_plan = parse_plan_execution_tasks(relevant_content_plan)
    else:
        print("Aviso: Secção 'Parte 3 – Plano de Execução do Projeto' não encontrada. Nenhuma tarefa de plano será extraída.")

    # Parse User Stories from Part 2
    user_story_section = re.search(r"## Parte 2 – Especificação Funcional(.*?)(?=## Parte \d+ –|$)", markdown_content, re.DOTALL | re.IGNORECASE)
    parsed_user_stories = []
    if user_story_section:
        relevant_content_us = user_story_section.group(1)
        parsed_user_stories = parse_user_stories(relevant_content_us)
    else:
        print("Aviso: Secção 'Parte 2 – Especificação Funcional' não encontrada. Nenhuma User Story será extraída.")

    # Combine tasks
    all_tasks = parsed_tasks_plan + parsed_user_stories

    if not all_tasks:
        print("Nenhuma tarefa ou User Story foi extraída no total. Verifique o formato do Markdown ou a lógica de parsing.")
        return

    df = pd.DataFrame(all_tasks)

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
