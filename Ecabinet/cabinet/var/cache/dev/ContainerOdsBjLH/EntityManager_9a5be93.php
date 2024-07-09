<?php

namespace ContainerOdsBjLH;

class EntityManager_9a5be93 extends \Doctrine\ORM\EntityManager implements \ProxyManager\Proxy\VirtualProxyInterface
{
    /**
     * @var \Doctrine\ORM\EntityManager|null wrapped object, if the proxy is initialized
     */
    private $valueHolder64aaa = null;

    /**
     * @var \Closure|null initializer responsible for generating the wrapped object
     */
    private $initializer8f368 = null;

    /**
     * @var bool[] map of public properties of the parent class
     */
    private static $publicProperties2385c = [
        
    ];

    public function getConnection()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getConnection', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getConnection();
    }

    public function getMetadataFactory()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getMetadataFactory', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getMetadataFactory();
    }

    public function getExpressionBuilder()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getExpressionBuilder', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getExpressionBuilder();
    }

    public function beginTransaction()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'beginTransaction', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->beginTransaction();
    }

    public function getCache()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getCache', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getCache();
    }

    public function transactional($func)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'transactional', array('func' => $func), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->transactional($func);
    }

    public function commit()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'commit', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->commit();
    }

    public function rollback()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'rollback', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->rollback();
    }

    public function getClassMetadata($className)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getClassMetadata', array('className' => $className), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getClassMetadata($className);
    }

    public function createQuery($dql = '')
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'createQuery', array('dql' => $dql), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->createQuery($dql);
    }

    public function createNamedQuery($name)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'createNamedQuery', array('name' => $name), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->createNamedQuery($name);
    }

    public function createNativeQuery($sql, \Doctrine\ORM\Query\ResultSetMapping $rsm)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'createNativeQuery', array('sql' => $sql, 'rsm' => $rsm), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->createNativeQuery($sql, $rsm);
    }

    public function createNamedNativeQuery($name)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'createNamedNativeQuery', array('name' => $name), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->createNamedNativeQuery($name);
    }

    public function createQueryBuilder()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'createQueryBuilder', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->createQueryBuilder();
    }

    public function flush($entity = null)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'flush', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->flush($entity);
    }

    public function find($className, $id, $lockMode = null, $lockVersion = null)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'find', array('className' => $className, 'id' => $id, 'lockMode' => $lockMode, 'lockVersion' => $lockVersion), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->find($className, $id, $lockMode, $lockVersion);
    }

    public function getReference($entityName, $id)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getReference', array('entityName' => $entityName, 'id' => $id), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getReference($entityName, $id);
    }

    public function getPartialReference($entityName, $identifier)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getPartialReference', array('entityName' => $entityName, 'identifier' => $identifier), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getPartialReference($entityName, $identifier);
    }

    public function clear($entityName = null)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'clear', array('entityName' => $entityName), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->clear($entityName);
    }

    public function close()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'close', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->close();
    }

    public function persist($entity)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'persist', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->persist($entity);
    }

    public function remove($entity)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'remove', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->remove($entity);
    }

    public function refresh($entity)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'refresh', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->refresh($entity);
    }

    public function detach($entity)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'detach', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->detach($entity);
    }

    public function merge($entity)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'merge', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->merge($entity);
    }

    public function copy($entity, $deep = false)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'copy', array('entity' => $entity, 'deep' => $deep), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->copy($entity, $deep);
    }

    public function lock($entity, $lockMode, $lockVersion = null)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'lock', array('entity' => $entity, 'lockMode' => $lockMode, 'lockVersion' => $lockVersion), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->lock($entity, $lockMode, $lockVersion);
    }

    public function getRepository($entityName)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getRepository', array('entityName' => $entityName), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getRepository($entityName);
    }

    public function contains($entity)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'contains', array('entity' => $entity), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->contains($entity);
    }

    public function getEventManager()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getEventManager', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getEventManager();
    }

    public function getConfiguration()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getConfiguration', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getConfiguration();
    }

    public function isOpen()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'isOpen', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->isOpen();
    }

    public function getUnitOfWork()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getUnitOfWork', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getUnitOfWork();
    }

    public function getHydrator($hydrationMode)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getHydrator', array('hydrationMode' => $hydrationMode), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getHydrator($hydrationMode);
    }

    public function newHydrator($hydrationMode)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'newHydrator', array('hydrationMode' => $hydrationMode), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->newHydrator($hydrationMode);
    }

    public function getProxyFactory()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getProxyFactory', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getProxyFactory();
    }

    public function initializeObject($obj)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'initializeObject', array('obj' => $obj), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->initializeObject($obj);
    }

    public function getFilters()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'getFilters', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->getFilters();
    }

    public function isFiltersStateClean()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'isFiltersStateClean', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->isFiltersStateClean();
    }

    public function hasFilters()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'hasFilters', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return $this->valueHolder64aaa->hasFilters();
    }

    /**
     * Constructor for lazy initialization
     *
     * @param \Closure|null $initializer
     */
    public static function staticProxyConstructor($initializer)
    {
        static $reflection;

        $reflection = $reflection ?? new \ReflectionClass(__CLASS__);
        $instance   = $reflection->newInstanceWithoutConstructor();

        \Closure::bind(function (\Doctrine\ORM\EntityManager $instance) {
            unset($instance->config, $instance->conn, $instance->metadataFactory, $instance->unitOfWork, $instance->eventManager, $instance->proxyFactory, $instance->repositoryFactory, $instance->expressionBuilder, $instance->closed, $instance->filterCollection, $instance->cache);
        }, $instance, 'Doctrine\\ORM\\EntityManager')->__invoke($instance);

        $instance->initializer8f368 = $initializer;

        return $instance;
    }

    protected function __construct(\Doctrine\DBAL\Connection $conn, \Doctrine\ORM\Configuration $config, \Doctrine\Common\EventManager $eventManager)
    {
        static $reflection;

        if (! $this->valueHolder64aaa) {
            $reflection = $reflection ?? new \ReflectionClass('Doctrine\\ORM\\EntityManager');
            $this->valueHolder64aaa = $reflection->newInstanceWithoutConstructor();
        \Closure::bind(function (\Doctrine\ORM\EntityManager $instance) {
            unset($instance->config, $instance->conn, $instance->metadataFactory, $instance->unitOfWork, $instance->eventManager, $instance->proxyFactory, $instance->repositoryFactory, $instance->expressionBuilder, $instance->closed, $instance->filterCollection, $instance->cache);
        }, $this, 'Doctrine\\ORM\\EntityManager')->__invoke($this);

        }

        $this->valueHolder64aaa->__construct($conn, $config, $eventManager);
    }

    public function & __get($name)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, '__get', ['name' => $name], $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        if (isset(self::$publicProperties2385c[$name])) {
            return $this->valueHolder64aaa->$name;
        }

        $realInstanceReflection = new \ReflectionClass('Doctrine\\ORM\\EntityManager');

        if (! $realInstanceReflection->hasProperty($name)) {
            $targetObject = $this->valueHolder64aaa;

            $backtrace = debug_backtrace(false, 1);
            trigger_error(
                sprintf(
                    'Undefined property: %s::$%s in %s on line %s',
                    $realInstanceReflection->getName(),
                    $name,
                    $backtrace[0]['file'],
                    $backtrace[0]['line']
                ),
                \E_USER_NOTICE
            );
            return $targetObject->$name;
        }

        $targetObject = $this->valueHolder64aaa;
        $accessor = function & () use ($targetObject, $name) {
            return $targetObject->$name;
        };
        $backtrace = debug_backtrace(true, 2);
        $scopeObject = isset($backtrace[1]['object']) ? $backtrace[1]['object'] : new \ProxyManager\Stub\EmptyClassStub();
        $accessor = $accessor->bindTo($scopeObject, get_class($scopeObject));
        $returnValue = & $accessor();

        return $returnValue;
    }

    public function __set($name, $value)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, '__set', array('name' => $name, 'value' => $value), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        $realInstanceReflection = new \ReflectionClass('Doctrine\\ORM\\EntityManager');

        if (! $realInstanceReflection->hasProperty($name)) {
            $targetObject = $this->valueHolder64aaa;

            $targetObject->$name = $value;

            return $targetObject->$name;
        }

        $targetObject = $this->valueHolder64aaa;
        $accessor = function & () use ($targetObject, $name, $value) {
            $targetObject->$name = $value;

            return $targetObject->$name;
        };
        $backtrace = debug_backtrace(true, 2);
        $scopeObject = isset($backtrace[1]['object']) ? $backtrace[1]['object'] : new \ProxyManager\Stub\EmptyClassStub();
        $accessor = $accessor->bindTo($scopeObject, get_class($scopeObject));
        $returnValue = & $accessor();

        return $returnValue;
    }

    public function __isset($name)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, '__isset', array('name' => $name), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        $realInstanceReflection = new \ReflectionClass('Doctrine\\ORM\\EntityManager');

        if (! $realInstanceReflection->hasProperty($name)) {
            $targetObject = $this->valueHolder64aaa;

            return isset($targetObject->$name);
        }

        $targetObject = $this->valueHolder64aaa;
        $accessor = function () use ($targetObject, $name) {
            return isset($targetObject->$name);
        };
        $backtrace = debug_backtrace(true, 2);
        $scopeObject = isset($backtrace[1]['object']) ? $backtrace[1]['object'] : new \ProxyManager\Stub\EmptyClassStub();
        $accessor = $accessor->bindTo($scopeObject, get_class($scopeObject));
        $returnValue = $accessor();

        return $returnValue;
    }

    public function __unset($name)
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, '__unset', array('name' => $name), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        $realInstanceReflection = new \ReflectionClass('Doctrine\\ORM\\EntityManager');

        if (! $realInstanceReflection->hasProperty($name)) {
            $targetObject = $this->valueHolder64aaa;

            unset($targetObject->$name);

            return;
        }

        $targetObject = $this->valueHolder64aaa;
        $accessor = function () use ($targetObject, $name) {
            unset($targetObject->$name);

            return;
        };
        $backtrace = debug_backtrace(true, 2);
        $scopeObject = isset($backtrace[1]['object']) ? $backtrace[1]['object'] : new \ProxyManager\Stub\EmptyClassStub();
        $accessor = $accessor->bindTo($scopeObject, get_class($scopeObject));
        $accessor();
    }

    public function __clone()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, '__clone', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        $this->valueHolder64aaa = clone $this->valueHolder64aaa;
    }

    public function __sleep()
    {
        $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, '__sleep', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;

        return array('valueHolder64aaa');
    }

    public function __wakeup()
    {
        \Closure::bind(function (\Doctrine\ORM\EntityManager $instance) {
            unset($instance->config, $instance->conn, $instance->metadataFactory, $instance->unitOfWork, $instance->eventManager, $instance->proxyFactory, $instance->repositoryFactory, $instance->expressionBuilder, $instance->closed, $instance->filterCollection, $instance->cache);
        }, $this, 'Doctrine\\ORM\\EntityManager')->__invoke($this);
    }

    public function setProxyInitializer(\Closure $initializer = null) : void
    {
        $this->initializer8f368 = $initializer;
    }

    public function getProxyInitializer() : ?\Closure
    {
        return $this->initializer8f368;
    }

    public function initializeProxy() : bool
    {
        return $this->initializer8f368 && ($this->initializer8f368->__invoke($valueHolder64aaa, $this, 'initializeProxy', array(), $this->initializer8f368) || 1) && $this->valueHolder64aaa = $valueHolder64aaa;
    }

    public function isProxyInitialized() : bool
    {
        return null !== $this->valueHolder64aaa;
    }

    public function getWrappedValueHolderValue()
    {
        return $this->valueHolder64aaa;
    }
}

if (!\class_exists('EntityManager_9a5be93', false)) {
    \class_alias(__NAMESPACE__.'\\EntityManager_9a5be93', 'EntityManager_9a5be93', false);
}
