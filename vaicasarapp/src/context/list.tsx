import React, { createContext, useState, useContext, ReactNode } from 'react';

interface ProviderProps {
  children: ReactNode;
}
export interface TaskProps {
  icon?: string;
  title: string;
  date: string;
  loja?: {
    name: string;
    category: string;
  };
  finished: boolean;
  description: string;
}

export interface TaskList {
  list: TaskProps[];
  setList: React.Dispatch<React.SetStateAction<TaskProps[]>>;
}

const TaskContext = createContext({} as TaskList);

export default function TaskProvider({ children }: ProviderProps) {
  const [list, setList] = useState<TaskProps[]>([
    {
      icon: 'silverware-fork-knife',
      title: 'Buffet',
      date: '21/08/2022',
      loja: { name: 'Patricia Xavier', category: 'Buffet' },
      finished: true,
      description: '',
    },
    {
      icon: 'book',
      title: 'Cerimônia',
      date: '22/08/2022',
      loja: { name: 'Patricia Xavier', category: 'Cerimônia' },
      finished: false,
      description: 'Fazer tal coisa',
    },
    {
      icon: 'party-popper',
      title: 'Festa',
      date: '23/08/2022',
      loja: { name: 'Ana Monteiro', category: 'Decoração' },
      finished: false,
      description: 'Fazer tal coisa',
    },
    {
      icon: 'map-marker-outline',
      title: 'Lua de Mel',
      date: '23/08/2022',
      loja: { name: 'CVC', category: 'Viagem' },
      finished: false,
      description: 'Fazer tal coisa',
    },
    {
      icon: 'music',
      title: 'Música',
      date: '23/08/2022',
      loja: { name: 'David Guetta', category: 'DJ' },
      finished: false,
      description: 'Fazer tal coisa',
    },
  ]);

  return (
    <TaskContext.Provider value={{ list, setList }}>
      {children}
    </TaskContext.Provider>
  );
}

export function useTask() {
  const context = useContext(TaskContext);

  const { list, setList } = context;

  return { list, setList };
}
