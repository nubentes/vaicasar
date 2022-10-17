import React, { createContext, useState, useContext, ReactNode } from 'react';
import { DayProps } from '../components/Calendar';

interface ProviderProps {
  children: ReactNode;
}

export interface StoreProps {
  name: string;
  category: string;
}
export interface TaskProps {
  icon?: string;
  title: string;
  scheduledDate?: DayProps | null;
  conclusionDate?: DayProps | null;
  store?: StoreProps;
  finished: boolean;
  description?: string;
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
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'Patricia Xavier', category: 'Buffet' },
      finished: true,
      description: '',
    },
    {
      icon: 'book',
      title: 'Cerimônia',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'Patricia Xavier', category: 'Cerimônia' },
      finished: false,
      description: 'Fazer tal coisa',
    },
    {
      icon: 'party-popper',
      title: 'Festa',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'Ana Monteiro', category: 'Decoração' },
      finished: false,
      description: 'Fazer tal coisa',
    },
    {
      icon: 'map-marker-outline',
      title: 'Lua de Mel',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'CVC', category: 'Viagem' },
      finished: false,
      description: 'Fazer tal coisa',
    },
    {
      icon: 'music',
      title: 'Música',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'David Guetta', category: 'DJ' },
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
